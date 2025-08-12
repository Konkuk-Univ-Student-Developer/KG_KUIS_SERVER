package backend.kg_kuis_server.graduation.service.dto;

import lombok.Builder;

@Builder
public record
GraduationCheckRow(
        String title,
        String critierion,
        String acquired,
        String lack,
        String detail,
        String result
) {
}
