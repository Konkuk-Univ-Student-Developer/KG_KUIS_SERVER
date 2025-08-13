package backend.kg_kuis_server.grade.service;

import backend.kg_kuis_server.global.exception.CustomException;
import backend.kg_kuis_server.grade.repository.MemberGradeRepository;
import backend.kg_kuis_server.grade.repository.entity.MemberGrade;
import backend.kg_kuis_server.grade.service.response.*;
import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.member.exception.MemberErrorCode;
import backend.kg_kuis_server.member.repository.MemberAcademicRepository;
import backend.kg_kuis_server.member.repository.MemberRepository;
import backend.kg_kuis_server.member.repository.entity.MemberAcademy;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GradeQueryService {

    private static final Map<String, Double> GRADE_POINT_MAP = Map.ofEntries(
            Map.entry("A+", 4.5),
            Map.entry("A", 4.0),
            Map.entry("B+", 3.5),
            Map.entry("B", 3.0),
            Map.entry("C+", 2.5),
            Map.entry("C", 2.0),
            Map.entry("D+", 1.5),
            Map.entry("D", 1.0),
            Map.entry("F", 0.0),
            Map.entry("P", 0.0)
    );

    private final MemberGradeRepository gradeRepository;
    private final MemberRepository memberRepository;
    private final MemberAcademicRepository memberAcademicRepository;

    public SemesterGradeResponse getSemesterGrades(Long memberId, Integer year, Semester semester) {
        MemberEntity member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND));
        MemberAcademy byMember = memberAcademicRepository.findByMember(member).orElseThrow(() -> new CustomException(MemberErrorCode.NOT_ACADEMY_INFO_EXIST));
        List<MemberGrade> memberGrades = gradeRepository.findMemberGradeByMemberAndCourse_CourseYearAndCourse_Semester(member, year, semester);

        int totalCredits = memberGrades.stream()
                .mapToInt(g -> g.getCourse().getCredit())
                .sum();

        double semesterGpa = 0.0;
        int gpaCredits = memberGrades.stream()
                .filter(g -> !"P".equalsIgnoreCase(g.getLetterGrade())) // P 제외
                .mapToInt(g -> g.getCourse().getCredit())
                .sum();

        if (gpaCredits > 0) {
            semesterGpa = memberGrades.stream()
                    .filter(g -> !"P".equalsIgnoreCase(g.getLetterGrade()))
                    .mapToDouble(g -> GRADE_POINT_MAP.getOrDefault(g.getLetterGrade(), 0.0) * g.getCourse().getCredit())
                    .sum() / gpaCredits;
        }

        boolean honors = semesterGpa >= 4.0;

        GradeSummary gradeSummary = GradeSummary.builder()
                .gpa(BigDecimal.valueOf(semesterGpa).setScale(2, RoundingMode.HALF_UP)) // 학기 gpa
                .gpaScale(byMember.getGpaScale())
                .earnedCredits(totalCredits) // 들은 학점 총합
                .registeredCredits(totalCredits) // 등록 학점 총합
                .probation(byMember.getEarlyGraduation()) // 기존 로직 유지
                .honors(honors) // 4.0 이상 여부
                .build();

        AtomicInteger counter = new AtomicInteger(1);

        List<GradeItem> gradeItems = memberGrades.stream().map(g -> GradeItem.builder().no(counter.getAndIncrement()).courseCode(g.getCourse().getCourseCode()).classNo(null).courseName(g.getCourse().getCourseName()).instructor(g.getCourse().getProfessor()).credit(g.getCourse().getCredit()).division(g.getCourse().getCourseCategory().name()).letterGrade(g.getLetterGrade()).gradingMethod(g.getCourse().getMethod()).gradeDetailResponse(GradeDetailResponse.from(g)).build()).toList();

        return SemesterGradeResponse.builder().year(year).semester(semester).summary(gradeSummary).items(gradeItems).build();
    }

    public AllSemesterGradesResponse getAllSemesters(Long memberId) {
        List<MemberGrade> rows = gradeRepository.findAllWithCourseByMemberId(memberId);

        Map<Key, List<MemberGrade>> grouped = rows.stream().collect(Collectors.groupingBy(mg -> new Key(mg.getCourse().getCourseYear(), mg.getCourse().getSemester())));

        List<SemesterBlock> blocks = grouped.entrySet().stream().sorted((e1, e2) -> { // 기본 최신순(desc)
            int cmp = Integer.compare(e2.getKey().year(), e1.getKey().year());
            if (cmp != 0) return cmp;
            return Integer.compare(e2.getKey().semester().ordinal(), e1.getKey().semester().ordinal());
        }).map(e -> toBlock(e.getKey(), e.getValue())).toList();


        return new AllSemesterGradesResponse(memberId, blocks);
    }

    private SemesterBlock toBlock(Key key, List<MemberGrade> list) {
        // 표 행
        List<SemesterCourseRow> courseRows = list.stream().map(SemesterCourseRow::from).toList();

        int applied = list.stream().map(mg -> nz(mg.getCourse().getCredit())).reduce(0, Integer::sum);

        int earned = list.stream().filter(mg -> !isFail(mg.getLetterGrade())).map(mg -> nz(mg.getCourse().getCredit())).reduce(0, Integer::sum);

        Double gpa = calcGpa(list);
        Double gpaScale = 4.5;

        Double percentage = applied == 0 ? null : (earned * 100.0 / applied);

        Integer rank = null;
        Integer total = null;

        SemesterSummary summary = new SemesterSummary(gpa, gpaScale, earned, applied, percentage, rank, total);

        return new SemesterBlock(key.year(), key.semester(), summary, courseRows);
    }

    private static boolean isFail(String letter) {
        if (letter == null) return false;
        return letter.equalsIgnoreCase("F") || letter.equalsIgnoreCase("NP");
    }

    private static Double calcGpa(List<MemberGrade> list) {
        int totalCredits = list.stream().map(mg -> nz(mg.getCourse().getCredit())).reduce(0, Integer::sum);
        if (totalCredits == 0) return null;

        double sum = 0.0;
        for (MemberGrade mg : list) {
            int cr = nz(mg.getCourse().getCredit());
            sum += gradePoint(mg.getLetterGrade()) * cr;
        }
        return Math.round((sum / totalCredits) * 100.0) / 100.0; // 소수 둘째 자리
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
            default -> 0.0;
        };
    }

    private static int nz(Integer v) {
        return v == null ? 0 : v;
    }


}
