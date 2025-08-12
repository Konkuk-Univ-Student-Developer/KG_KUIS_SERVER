package backend.kg_kuis_server.grade.service.response;


import backend.kg_kuis_server.course.domain.CourseCategory;

import java.util.Map;

public record SummaryRow(
        Map<CourseCategory, Double> byCategory, // 핵심: 카테고리별 수치
        Double totalEarned,   // 총취득학점(학점행), gpaRow는 전체 GPA, percentageRow는 전체 백분율
        Double totalApplied,  // 총신청학점(학점행에서 사용)
        Double totalDropped,  // 총포기학점(옵션)
        Double totalFn,       // 총 F/N 학점(학점행에서 사용)
        Double fullMark
) {
    public static SummaryRow empty() {
        return new SummaryRow(Map.of(), null, null, null, null, null);
    }
}
