package backend.kg_kuis_server.grade.service.response;

public record OverallSummaryResponse(
        SummaryRow creditRow,
        SummaryRow gpaRow,
        SummaryRow percentageRow,
        String overallRank
) {}

