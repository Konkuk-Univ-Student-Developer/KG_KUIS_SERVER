package backend.kg_kuis_server.grade.service.response;

public record OverallSummaryResponse(
        SummaryRow creditRow,      // "학점" 행
        SummaryRow gpaRow,         // "평점평균" 행
        SummaryRow percentageRow,  // "백분율" 행
        String overallRank         // "1/337" 같은 문자열
) {}

