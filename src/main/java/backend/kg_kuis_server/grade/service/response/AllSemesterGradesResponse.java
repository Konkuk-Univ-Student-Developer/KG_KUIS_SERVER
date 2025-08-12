package backend.kg_kuis_server.grade.service.response;

import java.util.List;

public record AllSemesterGradesResponse(
        Long memberId,
        List<SemesterBlock> semesters
) {}
