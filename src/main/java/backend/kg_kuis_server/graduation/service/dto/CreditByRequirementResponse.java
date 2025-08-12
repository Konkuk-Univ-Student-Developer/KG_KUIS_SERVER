package backend.kg_kuis_server.graduation.service.dto;

import java.util.List;

public record CreditByRequirementResponse(
        List<CreditByCategoryRow> rows,
        CreditCardSummary summary
) {}
