package backend.kg_kuis_server.graduation.service.dto;

public record CreditByCategoryRow(
        String programType,   // "원전공"
        String category,      // "기교", "심교", "전필+전선" 등 UI 라벨
        Integer requiredCredits,
        Integer acquiredCredits,
        Integer remainingCredits,
        String detailLink     // ex) "/courses?cat=기교"
) {}