package backend.kg_kuis_server.scholarship.service.dto;

import java.util.List;

public record MyApplicationListResponse(
        List<MyApplicationItemResponse> items
) {}