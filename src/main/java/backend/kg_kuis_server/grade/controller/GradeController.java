package backend.kg_kuis_server.grade.controller;

import backend.kg_kuis_server.grade.service.GradeQueryService;
import backend.kg_kuis_server.grade.service.OverallSummaryService;
import backend.kg_kuis_server.grade.service.response.AllSemesterGradesResponse;
import backend.kg_kuis_server.grade.service.response.OverallSummaryResponse;
import backend.kg_kuis_server.grade.service.response.SemesterGradeResponse;
import backend.kg_kuis_server.member.domain.Semester;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/members", produces = "application/json")
@RequiredArgsConstructor
@Tag(name = "성적 API", description = "성적/요약 조회 API")
public class GradeController {

    private final GradeQueryService gradeService;
    private final OverallSummaryService overallSummaryService;

    @Operation(
            summary = "학기별 성적 조회",
            description = "회원의 특정 학년도/학기 성적을 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = SemesterGradeResponse.class))),
            @ApiResponse(responseCode = "400", description = "요청 파라미터 오류", content = @Content),
            @ApiResponse(responseCode = "404", description = "데이터 없음", content = @Content)
    })
    @GetMapping("/{memberId}/grades")
    public ResponseEntity<SemesterGradeResponse> getSemesterGrades(
            @Parameter(name = "memberId", description = "회원 ID", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int64", example = "1"))
            @PathVariable Long memberId,
            @Parameter(name = "year", description = "학년도", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(type = "integer", example = "2025", minimum = "2000"))
            @RequestParam int year,
            @Parameter(name = "semester", description = "학기", required = true, in = ParameterIn.QUERY,
                    schema = @Schema(implementation = Semester.class, allowableValues = {"FIRST", "SECOND", "SUMMER", "WINTER"}, example = "FIRST"))
            @RequestParam Semester semester
    ) {
        return ResponseEntity.ok(gradeService.getSemesterGrades(memberId, year, semester));
    }

    @Operation(
            summary = "모든 학기 성적 조회",
            description = "회원의 모든 학년도/학기 성적을 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = SemesterGradeResponse.class))),
            @ApiResponse(responseCode = "400", description = "요청 파라미터 오류", content = @Content),
            @ApiResponse(responseCode = "404", description = "데이터 없음", content = @Content)
    })
    @GetMapping("/{memberId}/allgrades")
    public ResponseEntity<AllSemesterGradesResponse> getAllGrades(@Parameter(name = "memberId", description = "회원 ID", required = true, in = ParameterIn.PATH,
            schema = @Schema(type = "integer", format = "int64", example = "1"))
                                                                  @PathVariable Long memberId) {
        return ResponseEntity.ok(gradeService.getAllSemesters(memberId));
    }

    @Operation(
            summary = "전체 성적 요약 조회",
            description = "회원의 누적 성적 요약(총 이수학점, 평균 등)을 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = OverallSummaryResponse.class))),
            @ApiResponse(responseCode = "404", description = "데이터 없음", content = @Content)
    })
    @GetMapping("/{memberId}/summaries")
    public ResponseEntity<OverallSummaryResponse> getSummaries(
            @Parameter(name = "memberId", description = "회원 ID", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int64", example = "1"))
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(overallSummaryService.getOverall(memberId));
    }
}
