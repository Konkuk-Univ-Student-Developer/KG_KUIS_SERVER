package backend.kg_kuis_server.scholarship.controller;

import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.scholarship.service.ScholarshipQueryService;
import backend.kg_kuis_server.scholarship.service.dto.AvailableListResponse;
import backend.kg_kuis_server.scholarship.service.dto.DisbursementHistoryResponse;
import backend.kg_kuis_server.scholarship.service.dto.MyApplicationListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api/v1/scholarships", produces = "application/json")
@RequiredArgsConstructor
@Tag(name = "장학금 API", description = "장학금 조회 API")
public class ScholarshipController {

    private final ScholarshipQueryService scholarshipQueryService;

    // 상단: 신청 가능 목록
    @Operation(
            summary = "신청 가능 장학금 목록 조회",
            description = "특정 연도와 학기에 대해 회원의 신청 가능 장학금 목록을 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = AvailableListResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 파라미터",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "회원 또는 데이터 없음",
                    content = @Content)
    })
    @GetMapping("/{memberId}/available")
    public AvailableListResponse available(
            @Parameter(
                    name = "memberId",
                    description = "회원 ID",
                    required = true,
                    in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int64", example = "1")
            )
            @PathVariable Long memberId,
            @Parameter(
                    name = "year",
                    description = "학년도",
                    required = true,
                    in = ParameterIn.QUERY,
                    schema = @Schema(type = "integer", example = "2025", minimum = "2000")
            )
            @RequestParam @NotNull Integer year,
            @Parameter(
                    name = "semester",
                    description = "학기",
                    required = true,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = Semester.class, allowableValues = {"FIRST","SECOND","SUMMER","WINTER"}, example = "FIRST")
            )
            @RequestParam @NotNull Semester semester
    ) {
        return scholarshipQueryService.getAvailable(year, semester, memberId);
    }

    @Operation(
            summary = "나의 장학금 신청 내역 조회",
            description = "회원의 장학금 신청 내역을 기간 필터(from~to)로 조회합니다. 날짜 형식은 ISO-8601(yyyy-MM-dd)입니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = MyApplicationListResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 날짜 형식 등",
                    content = @Content)
    })
    @GetMapping("/{memberId}/applications")
    public MyApplicationListResponse myApplications(
            @Parameter(name = "memberId", description = "회원 ID", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int64", example = "1"))
            @PathVariable Long memberId,
            @Parameter(name = "from", description = "조회 시작일(포함). ISO 날짜(yyyy-MM-dd).", in = ParameterIn.QUERY,
                    schema = @Schema(type = "string", format = "date", example = "2025-01-01"))
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @Parameter(name = "to", description = "조회 종료일(포함). ISO 날짜(yyyy-MM-dd).", in = ParameterIn.QUERY,
                    schema = @Schema(type = "string", format = "date", example = "2025-12-31"))
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return scholarshipQueryService.getMyApplications(memberId, from, to);
    }

    @Operation(
            summary = "장학금 지급 이력 조회",
            description = "회원의 장학금 지급(수혜) 이력을 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = DisbursementHistoryResponse.class))),
            @ApiResponse(responseCode = "404", description = "데이터 없음",
                    content = @Content)
    })
    @GetMapping("/{memberId}/disbursements")
    public ResponseEntity<DisbursementHistoryResponse> myApplications(
            @Parameter(name = "memberId", description = "회원 ID", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "integer", format = "int64", example = "1"))
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(scholarshipQueryService.getHistory(memberId));
    }
}
