package backend.kg_kuis_server.graduation.service.dto;

import java.util.List;

public record GraduationCheckResponse(
        List<GraduationCheckRow> bachelor,
        List<GraduationCheckRow> major
) {
}