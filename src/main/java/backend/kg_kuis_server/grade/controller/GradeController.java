package backend.kg_kuis_server.grade.controller;

import backend.kg_kuis_server.grade.service.GradeQueryService;
import backend.kg_kuis_server.grade.service.OverallSummaryService;
import backend.kg_kuis_server.grade.service.response.OverallSummaryResponse;
import backend.kg_kuis_server.grade.service.response.SemesterGradeResponse;
import backend.kg_kuis_server.member.domain.Semester;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class GradeController {

    private final GradeQueryService gradeService;
    private final OverallSummaryService overallSummaryService;

    // 성적 학기별 조회
    @GetMapping("/{memberId}/grades")
    public ResponseEntity<SemesterGradeResponse> getSemesterGrades(
            @PathVariable Long memberId,
            @RequestParam int year,
            @RequestParam Semester semester
    ) {
        return ResponseEntity.ok(gradeService.getSemesterGrades(memberId, year, semester));
    }

    @GetMapping("/{memberId}/summaries")
    public ResponseEntity<OverallSummaryResponse> getSummaries(@PathVariable Long memberId) {
        return ResponseEntity.ok(overallSummaryService.getOverall(memberId));
    }

}
