package backend.kg_kuis_server.graduation.service;

import backend.kg_kuis_server.course.domain.CourseCategory;
import backend.kg_kuis_server.course.domain.SubjectClassification;
import backend.kg_kuis_server.grade.repository.MemberGradeRepository;
import backend.kg_kuis_server.course.repository.SubjectEntityRepository;
import backend.kg_kuis_server.graduation.repository.GraduationCreditRequirementRepository;
import backend.kg_kuis_server.graduation.repository.entity.GraduationCreditRequirement;
import backend.kg_kuis_server.graduation.service.dto.*;
import backend.kg_kuis_server.global.exception.CustomException;
import backend.kg_kuis_server.member.repository.MemberRepository;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static backend.kg_kuis_server.member.exception.MemberErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GraduationDashboardService {

    private static final List<CourseCategory> CATEGORY_DISPLAY_ORDER =
            List.of(CourseCategory.values());

    private final MemberRepository memberRepository;
    private final MemberGradeRepository memberGradeRepository;
    private final GraduationCreditRequirementRepository requirementRepository;
    private final SubjectEntityRepository subjectEntityRepository;

    @Transactional(readOnly = true)
    public GraduationDashboardResponse get(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(NOT_FOUND));

        List<GraduationCreditRequirement> requirements = requirementRepository.findAll();

        // ② 카테고리별 취득 학점 합계 (F/NP 제외)  -> Enum 키로 맵핑
        Map<CourseCategory, Integer> earnedByCategory =
                toEnumMap(memberGradeRepository.sumEarnedCreditsGroupByCategory(memberId));

        // 좌측 표: 요건 vs 취득 vs 잔여
        List<GraduationCreditRow> creditRows = requirements.stream()
                .map(r -> {
                    String label = r.getCategory();
                    int required = r.getRequiredCredits();
                    int acquired = findAcquiredByLabel(earnedByCategory, label); // 라벨→Enum 매핑 후 조회
                    int remaining = Math.max(0, required - acquired);
                    return new GraduationCreditRow("원전공", label, required, acquired, remaining);
                })
                .toList();

        int totalEarned = memberGradeRepository.sumEarnedCredits(memberId);
        int registered = 0;
        int duplicatedCredits = 0;
        int totalRequired = creditRows.stream().mapToInt(GraduationCreditRow::required).sum();
        int remainingAll = Math.max(0, totalRequired - totalEarned);
        CreditCardSummary card = new CreditCardSummary(totalEarned, registered, duplicatedCredits, remainingAll);

        List<CountRow> basicLiberal = List.of(
                new CountRow("SW", 2, subjectEntityRepository.countByClassification(SubjectClassification.SW)),
                new CountRow("글쓰기", 1, subjectEntityRepository.countByClassification(SubjectClassification.글쓰기)),
                new CountRow("외국어", 1, subjectEntityRepository.countByClassification(SubjectClassification.외국어)),
                new CountRow("인성", 1, subjectEntityRepository.countByClassification(SubjectClassification.인성)),
                new CountRow("취창업", 1, subjectEntityRepository.countByClassification(SubjectClassification.취창업))
        );

        List<CountRow> advancedLiberal = List.of(
                new CountRow("글로벌인재양성", 2, subjectEntityRepository.countByClassification(SubjectClassification.글로벌인재양성)),
                new CountRow("사고력증진", 2, subjectEntityRepository.countByClassification(SubjectClassification.사고력증진)),
                new CountRow("외국어", 2, subjectEntityRepository.countByClassification(SubjectClassification.외국어))
        );
        List<CountRow> englishCounts = List.of(
                new CountRow("원전공", 2, subjectEntityRepository.countByClassification(SubjectClassification.영어)),
                new CountRow("다전공", 0, 0)
        );

        Map<CourseCategory, Integer> registeredByCat = Collections.emptyMap();

        List<CategoryCoursesBlock> categoryBlocks = CATEGORY_DISPLAY_ORDER.stream()
                .map(cat -> buildCategoryBlock(memberId, cat, earnedByCategory, registeredByCat))
                .filter(Objects::nonNull)
                .toList();

        return new GraduationDashboardResponse(
                creditRows,
                card,
                basicLiberal,
                advancedLiberal,
                englishCounts,
                List.of(),
                categoryBlocks
        );
    }

    private CategoryCoursesBlock buildCategoryBlock(
            Long memberId,
            CourseCategory category,
            Map<CourseCategory, Integer> earnedByCategory,
            Map<CourseCategory, Integer> registeredByCategory
    ) {
        var rows = memberGradeRepository.findAllByMemberAndCategory(memberId, category);

        boolean hasAny =
                !rows.isEmpty() ||
                        earnedByCategory.containsKey(category) ||
                        registeredByCategory.containsKey(category);

        if (!hasAny) return null;

        var items = rows.stream()
                .map(mc -> new CategoryCourseItem(
                        mc.getCourse().getCourseYear(),
                        mc.getCourse().getSemester(),
                        mc.getCourse().getGrade(),
                        mc.getCourse().getCourseNumber(),
                        mc.getCourse().getCourseName(),
                        null,   // 세부 분류
                        mc.getCourse().getCredit(),
                        mc.getLetterGrade()
                ))
                .collect(Collectors.toList());

        String label = displayLabel(category);

        return new CategoryCoursesBlock(
                label,
                nz(earnedByCategory.get(category)),
                nz(registeredByCategory.getOrDefault(category, 0)),
                items
        );
    }

    private int findAcquiredByLabel(Map<CourseCategory, Integer> earnedByCat, String label) {
        CourseCategory cat = mapLabelToEnum(label);
        if (cat == null) return 0;
        return nz(earnedByCat.get(cat));
    }

    private String displayLabel(CourseCategory c) {
        return switch (c) {
            case 기교 -> "기교";
            case 심교 -> "심교";
            case 반교 -> "반교";
            case 지교 -> "지교";
            case 지필 -> "지필";
            case 전필전선 -> "전필전선";
            case 전기 -> "전기";
            case 전필 -> "전필";
            case 일선 -> "일선";
            case 기타 -> "기타";
            case 교필 -> "교필";
            case 교선 -> "교선";
            case 다전공 -> "다전공";
            case 부전공 -> "부전공";
            case 교직 -> "교직";
            case 전공 -> "전공";
            case 교양 -> "교양";
            case 연계전공 -> "연계전공";
        };
    }

    /**
     * 라벨(String) → Enum
     */
    private CourseCategory mapLabelToEnum(String label) {
        return switch (label) {
            case "기교" -> CourseCategory.기교;
            case "심교" -> CourseCategory.심교;
            case "반교" -> CourseCategory.반교;
            case "지교" -> CourseCategory.지교;
            case "지필" -> CourseCategory.지필;
            case "전필전선" -> CourseCategory.전필전선;
            case "전기" -> CourseCategory.전기;
            case "일선" -> CourseCategory.일선;
            case "기타" -> CourseCategory.기타;
            default -> null;
        };
    }

    /**
     * List<Object[]> → Map<CourseCategory, Integer> (Enum 키 안전)
     */
    private static Map<CourseCategory, Integer> toEnumMap(List<Object[]> rows) {
        Map<CourseCategory, Integer> m = new LinkedHashMap<>();
        for (Object[] r : rows) {
            CourseCategory key = (r[0] instanceof CourseCategory cc)
                    ? cc
                    : CourseCategory.valueOf(String.valueOf(r[0]));
            Integer val = ((Number) r[1]).intValue();
            m.put(key, val);
        }
        return m;
    }

    private static int nz(Integer i) {
        return i == null ? 0 : i;
    }
}
