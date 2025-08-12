package backend.kg_kuis_server.grade.service;

import backend.kg_kuis_server.course.domain.CourseCategory;
import backend.kg_kuis_server.grade.repository.MemberGradeRepository;
import backend.kg_kuis_server.grade.repository.entity.MemberGrade;
import backend.kg_kuis_server.grade.service.response.OverallSummaryResponse;
import backend.kg_kuis_server.grade.service.response.SummaryRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OverallSummaryService {

    private final MemberGradeRepository memberGradeRepository;

    // 화면에 보일 카테고리 정렬(원하는 순서로 바꿔도 됨)
    private static final List<CourseCategory> DISPLAY_ORDER = List.of(
            CourseCategory.전필, CourseCategory.전선, CourseCategory.전필전선, CourseCategory.전기,
            CourseCategory.교필, CourseCategory.교선,
            CourseCategory.기교, CourseCategory.심교, CourseCategory.지교, CourseCategory.지필,
            CourseCategory.일선, CourseCategory.반교,
            CourseCategory.기타
    );

    public OverallSummaryResponse getOverall(Long memberId) {
        List<MemberGrade> rows = memberGradeRepository.findAllWithCourseByMemberId(memberId);

        // 카테고리별 그룹
        Map<CourseCategory, List<MemberGrade>> byCat =
                rows.stream().collect(Collectors.groupingBy(mg -> mg.getCourse().getCourseCategory()));

        // --- 카테고리별 값 만들기 ---
        Map<CourseCategory, Double> earnedByCat = new LinkedHashMap<>();
        Map<CourseCategory, Double> gpaByCat = new LinkedHashMap<>();
        Map<CourseCategory, Double> pctByCat = new LinkedHashMap<>();

        for (CourseCategory cat : DISPLAY_ORDER) {
            double applied = applied(byCat, cat);
            double earned = earned(byCat, cat);
            Double gpa = calcGpa(byCat.getOrDefault(cat, List.of()));
            Double pct = applied == 0 ? null : (earned * 100.0 / applied);

            // 학점 행(취득학점)
            if (earned > 0) earnedByCat.put(cat, round1(earned)); // 소수1자리 맞춤(원하면 정수로)
            else earnedByCat.put(cat, 0.0);

            // GPA 행
            gpaByCat.put(cat, gpa);

            // 백분율 행
            pctByCat.put(cat, pct == null ? null : round1(pct));
        }

        // 총계
        double totalApplied = rows.stream().mapToInt(mg -> nz(mg.getCourse().getCredit())).sum();
        double totalEarned = rows.stream()
                .filter(mg -> !isFail(mg.getLetterGrade()))
                .mapToInt(mg -> nz(mg.getCourse().getCredit()))
                .sum();
        double totalFn = rows.stream()
                .filter(mg -> isFail(mg.getLetterGrade()))
                .mapToInt(mg -> nz(mg.getCourse().getCredit()))
                .sum();
        double totalDropped = 0.0; // 필요시 별도 로직

        Double gpaOverall = calcGpa(rows);
        Double pctOverall = totalApplied == 0 ? null : (totalEarned * 100.0 / totalApplied);

        SummaryRow creditRow = new SummaryRow(
                earnedByCat,
                round1(totalEarned),
                round1(totalApplied),
                round1(totalDropped),
                round1(totalFn),
                null
        );
        SummaryRow gpaRow = new SummaryRow(
                gpaByCat,
                gpaOverall,    // totalEarned 자리에 "전체 GPA" 넣음 (행 의미에 맞게)
                null, null, null,
                4.5
        );
        SummaryRow percentageRow = new SummaryRow(
                pctByCat,
                pctOverall == null ? null : round1(pctOverall),
                null, null, null,
                100.0
        );

        String overallRank = "1/337"; // 임의 값(있으면 주입)

        return new OverallSummaryResponse(creditRow, gpaRow, percentageRow, overallRank);
    }

    // ===== 헬퍼 =====
    private static int nz(Integer v) {
        return v == null ? 0 : v;
    }

    private static double round1(double v) {
        return Math.round(v * 10.0) / 10.0;
    }

    private static boolean isFail(String letter) {
        if (letter == null) return false;
        String s = letter.toUpperCase();
        return s.equals("F") || s.equals("NP"); // 필요하면 확장
    }

    private static boolean excludedFromGpa(String letter) {
        if (letter == null) return false;
        String s = letter.toUpperCase();
        return s.equals("P") || s.equals("NP"); // P/NP는 GPA 제외
    }

    private static double applied(Map<CourseCategory, List<MemberGrade>> byCat, CourseCategory cat) {
        return byCat.getOrDefault(cat, List.of()).stream()
                .mapToInt(mg -> nz(mg.getCourse().getCredit())).sum();
    }

    private static double earned(Map<CourseCategory, List<MemberGrade>> byCat, CourseCategory cat) {
        return byCat.getOrDefault(cat, List.of()).stream()
                .filter(mg -> !isFail(mg.getLetterGrade()))
                .mapToInt(mg -> nz(mg.getCourse().getCredit())).sum();
    }

    private static Double calcGpa(List<MemberGrade> list) {
        if (list == null || list.isEmpty()) return null;
        int credits = 0;
        double points = 0.0;
        for (MemberGrade mg : list) {
            String lg = mg.getLetterGrade();
            if (excludedFromGpa(lg)) continue;      // P/NP 제외
            int cr = nz(mg.getCourse().getCredit());
            credits += cr;
            points += gradePoint(lg) * cr;
        }
        if (credits == 0) return null;
        double g = points / credits;
        return Math.round(g * 100.0) / 100.0;       // 소수 둘째 자리
    }

    private static double gradePoint(String letter) {
        if (letter == null) return 0.0;
        return switch (letter.toUpperCase()) {
            case "A+" -> 4.5;
            case "A" -> 4.0;
            case "B+" -> 3.5;
            case "B" -> 3.0;
            case "C+" -> 2.5;
            case "C" -> 2.0;
            case "D+" -> 1.5;
            case "D" -> 1.0;
            default -> 0.0; // F/NP 등
        };
    }
}

