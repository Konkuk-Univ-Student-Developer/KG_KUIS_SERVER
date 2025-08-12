package backend.kg_kuis_server.grade.service.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GradeSummary(
        BigDecimal gpa,            // 4.5
        BigDecimal gpaScale,       // 4.5
        int earnedCredits,     // 18
        int registeredCredits, // 18
        boolean probation,     // N -> false
        boolean honors         // Y -> true
) {}