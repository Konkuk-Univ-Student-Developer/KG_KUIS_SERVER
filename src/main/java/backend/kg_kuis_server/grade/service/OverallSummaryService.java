package backend.kg_kuis_server.grade.service;

import backend.kg_kuis_server.course.domain.CourseCategory;
import backend.kg_kuis_server.grade.repository.MemberGradeRepository;
import backend.kg_kuis_server.grade.repository.entity.MemberGrade;
import backend.kg_kuis_server.grade.service.response.OverallSummaryResponse;
import backend.kg_kuis_server.grade.service.response.SummaryRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OverallSummaryService {

    private final MemberGradeRepository memberGradeRepository;

    /** 화면에 보여줄 7개 그룹의 고정 순서 */
    private static final List<CourseCategory> GROUP_ORDER = List.of(
            CourseCategory.전공,
            CourseCategory.교양,
            CourseCategory.다전공,
            CourseCategory.부전공,
            CourseCategory.연계전공,
            CourseCategory.교직,
            CourseCategory.기타
    );

    /**
     * 세부 카테고리 → 상위 그룹(7개 제한) 매핑.
     * - 전공: 전필/전필전선/전기/전선
     * - 교양: 교필/교선/기교/심교/지교/반교
     * - 나머지는 기타 (일선/지필/기타 등)
     */
    private static final Map<CourseCategory, CourseCategory> CAT_TO_GROUP = Map.ofEntries(
            // 전공 통합
            Map.entry(CourseCategory.전필, CourseCategory.전공),
            Map.entry(CourseCategory.전필전선, CourseCategory.전공),
            Map.entry(CourseCategory.전기, CourseCategory.전공),

            Map.entry(CourseCategory.교필, CourseCategory.교양),
            Map.entry(CourseCategory.교선, CourseCategory.교양),
            Map.entry(CourseCategory.기교, CourseCategory.교양),
            Map.entry(CourseCategory.심교, CourseCategory.교양),
            Map.entry(CourseCategory.지교, CourseCategory.교양),
            Map.entry(CourseCategory.반교, CourseCategory.교양)
    );

    /** 메인 엔드포인트: 7개 그룹으로 집계해서 반환 */
    public OverallSummaryResponse getOverall(Long memberId) {
        List<MemberGrade> rows = memberGradeRepository.findAllWithCourseByMemberId(memberId);

        Map<CourseCategory, List<MemberGrade>> byGroup = rows.stream()
                .collect(Collectors.groupingBy(mg ->
                        mapToGroupSafe(mg.getCourse() == null ? null : mg.getCourse().getCourseCategory())
                ));

        Map<CourseCategory, Double> earnedByGroup = new LinkedHashMap<>();
        Map<CourseCategory, Double> gpaByGroup = new LinkedHashMap<>();
        Map<CourseCategory, Double> pctByGroup = new LinkedHashMap<>();

        for (CourseCategory group : GROUP_ORDER) {
            List<MemberGrade> groupRows = byGroup.getOrDefault(group, List.of());

            double applied = groupRows.stream().mapToInt(mg -> nz(mg.getCourse() == null ? null : mg.getCourse().getCredit())).sum();
            double earned = groupRows.stream()
                    .filter(mg -> !isFail(mg.getLetterGrade()))
                    .mapToInt(mg -> nz(mg.getCourse() == null ? null : mg.getCourse().getCredit()))
                    .sum();

            Double gpa = calcGpa(groupRows);
            Double pct = applied == 0 ? null : (gpa / 4.5);

            earnedByGroup.put(group, round1(earned));                          // 학점 행
            gpaByGroup.put(group, gpa);                                        // GPA 행
            pctByGroup.put(group, pct == null ? null : round1(pct) * 100);           // 백분율 행
        }

        // 4) 전체 총계
        double totalApplied = rows.stream().mapToInt(mg -> nz(mg.getCourse() == null ? null : mg.getCourse().getCredit())).sum();
        double totalEarned = rows.stream()
                .filter(mg -> !isFail(mg.getLetterGrade()))
                .mapToInt(mg -> nz(mg.getCourse() == null ? null : mg.getCourse().getCredit()))
                .sum();
        double totalFn = rows.stream()
                .filter(mg -> isFail(mg.getLetterGrade()))
                .mapToInt(mg -> nz(mg.getCourse() == null ? null : mg.getCourse().getCredit()))
                .sum();
        double totalDropped = 0.0;

        Double gpaOverall = calcGpa(rows);
        Double pctOverall = totalApplied == 0 ? null : (totalEarned * 100.0 / totalApplied);

        SummaryRow creditRow = new SummaryRow(
                earnedByGroup,
                round1(totalEarned),
                round1(totalApplied),
                round1(totalDropped),
                round1(totalFn),
                null
        );
        SummaryRow gpaRow = new SummaryRow(
                gpaByGroup,
                gpaOverall,     // 전체 GPA
                null, null, null,
                4.5
        );
        SummaryRow percentageRow = new SummaryRow(
                pctByGroup,
                pctOverall == null ? null : round1(pctOverall),
                null, null, null,
                100.0
        );

        String overallRank = "150/337";

        return new OverallSummaryResponse(creditRow, gpaRow, percentageRow, overallRank);
    }

    // ===== 유틸 =====

    private static CourseCategory mapToGroupSafe(CourseCategory cat) {
        if (cat == null) return CourseCategory.기타;
        CourseCategory mapped = CAT_TO_GROUP.get(cat);
        if (mapped != null) return mapped;

        // (옵션) 다전공/부전공/연계전공/교직을 별도 식별할 수 있는 필드가 있으면 여기에 조건 분기 추가
        return CourseCategory.기타;
    }

    private static boolean isFail(String letter) {
        if (letter == null) return false;
        return "F".equals(letter.trim()); // 필요 시 "NP" 등 추가
    }

    private static int nz(Integer v) { return v == null ? 0 : v; }

    private static double round1(double v) { return Math.round(v * 10.0) / 10.0; }

    private static double round2(double v) { return Math.round(v * 100.0) / 100.0; }

    private static Double calcGpa(List<MemberGrade> rows) {
        if (rows == null || rows.isEmpty()) return null;
        double pts = 0.0;
        int creds = 0;
        for (MemberGrade mg : rows) {
            Integer c = (mg.getCourse() == null) ? 0 : mg.getCourse().getCredit();
            String lg = mg.getLetterGrade();
            if (c == null || c <= 0 || lg == null) continue;

            Double p = toPoint(lg);
            if (p == null) continue; // P/NP 등 GPA 제외
            pts += p * c;
            creds += c;
        }
        return creds == 0 ? null : round2(pts / creds);
    }

    private static Double toPoint(String letter) {
        if (letter == null) return null;
        return switch (letter.trim()) {
            case "A+" -> 4.5;
            case "A"  -> 4.0;
            case "A-" -> 3.7;
            case "B+" -> 3.5;
            case "B"  -> 3.0;
            case "B-" -> 2.7;
            case "C+" -> 2.5;
            case "C"  -> 2.0;
            case "C-" -> 1.7;
            case "D+" -> 1.5;
            case "D"  -> 1.0;
            case "D-" -> 0.7;
            case "F"  -> 0.0;
            // Pass/비가산 과목
            case "P", "NP", "S", "U" -> null;
            default -> null;
        };
    }
}
