package backend.kg_kuis_server.graduation.service.dto;

import java.util.List;

public record GraduationDashboardResponse(
        List<GraduationCreditRow> creditRows,
        CreditCardSummary creditSummary,
        List<CountRow> basicLiberal,
        List<CountRow> advancedLiberal,
        List<CountRow> englishCounts,
        List<DuplicateCourseRow> duplicates,
        // ★ 추가: 카테고리별 수강 과목 표
        List<CategoryCoursesBlock> categoryCourses
) {}
