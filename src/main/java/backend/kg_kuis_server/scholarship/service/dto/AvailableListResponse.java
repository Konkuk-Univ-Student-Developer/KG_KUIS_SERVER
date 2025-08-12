package backend.kg_kuis_server.scholarship.service.dto;

import backend.kg_kuis_server.member.domain.Semester;

import java.util.List;

public record AvailableListResponse(
        Integer year,
        Semester semester,
        List<AvailableScholarshipResponse> items
) {}