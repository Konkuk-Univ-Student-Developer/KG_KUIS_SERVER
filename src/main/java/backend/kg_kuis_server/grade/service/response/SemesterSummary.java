package backend.kg_kuis_server.grade.service.response;

public record SemesterSummary(
        Double gpa,         // 평점 평균 (null 가능)
        Double gpaScale,    // 4.5 등
        Integer earnedCredits,  // 취득학점
        Integer appliedCredits, // 신청학점
        Double percentage,      // 백분율(예: 100.0). 계산 규칙 맞춰 조정
        Integer rank,           // 학기별 석차
        Integer totalInSemester // 해당 학기 수강생 수(혹은 석차 母수)
) {}
