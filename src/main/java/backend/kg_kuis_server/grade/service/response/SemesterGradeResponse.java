package backend.kg_kuis_server.grade.service.response;

import backend.kg_kuis_server.member.domain.Semester;
import lombok.Builder;

import java.util.List;

@Builder
public record SemesterGradeResponse(Integer year, Semester semester, GradeSummary summary, List<GradeItem> items) {
}
