package backend.kg_kuis_server.graduation.service.dto;

public record CreditCardSummary(
        Integer totalEarned,      // 총 취득 학점
        Integer totalRegistered,  // 수강 신청 학점
        Integer duplicated,       // 중복 학점
        Integer remainingAll      // 전체 잔여 학점
) {}
