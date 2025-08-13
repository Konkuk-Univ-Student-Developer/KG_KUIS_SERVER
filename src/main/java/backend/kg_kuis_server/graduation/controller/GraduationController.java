package backend.kg_kuis_server.graduation.controller;

import backend.kg_kuis_server.graduation.service.GraduationDashboardService;
import backend.kg_kuis_server.graduation.service.GraduationService;
import backend.kg_kuis_server.graduation.service.dto.GraduationCheckResponse;
import backend.kg_kuis_server.graduation.service.dto.GraduationDashboardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "졸업 API", description = "졸업 요건 확인 API")
public class GraduationController {

    private final GraduationService graduationService;
    private final GraduationDashboardService graduationDashboardService;

    @GetMapping("/{memberId}/graduations/check")
    @Operation(
            summary = "졸업 요건 충족 여부 확인",
            description = """
                    특정 회원의 졸업 요건 충족 여부를 확인합니다.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "졸업 요건 확인 결과",
                            content = @Content(
                                    schema = @Schema(implementation = GraduationCheckResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "해당 회원을 찾을 수 없음"
                    )
            }
    )
    public GraduationCheckResponse check(
            @Parameter(
                    description = "회원 ID",
                    example = "1"
            )
            @PathVariable(name = "memberId") Long memberId
    ) {
        return graduationService.check(memberId);
    }

    @GetMapping("/{memberId}/graduations/dashboard")
    @Operation(
            summary = "취득학점 확인원",
            description = """
                    특정 회원의 취득학점 확인원을 확인합니다.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "졸업 요건 확인 결과",
                            content = @Content(
                                    schema = @Schema(implementation = GraduationCheckResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "해당 회원을 찾을 수 없음"
                    )
            }
    )
    public ResponseEntity<GraduationDashboardResponse> checkDashboard(
            @Parameter(
                    description = "회원 ID",
                    example = "1"
            )
            @PathVariable(name = "memberId") Long memberId
    ) {
        return ResponseEntity.ok(graduationDashboardService.get(memberId));
    }
}