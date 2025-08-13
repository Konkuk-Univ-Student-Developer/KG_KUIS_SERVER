package backend.kg_kuis_server.member.controller;

import backend.kg_kuis_server.member.service.MemberDashboardService;
import backend.kg_kuis_server.member.service.dto.MemberDashboardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "졸업 API", description = "졸업 API")
public class MemberDashboardController {

    private final MemberDashboardService service;

    @GetMapping("/{memberId}/dashboard")
    @Operation(
            summary = "졸업화면 대시보드 조회",
            description = """
                    지정한 회원 ID에 해당하는 대시보드 데이터를 반환합니다.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "대시보드 조회 성공",
                            content = @Content(schema = @Schema(implementation = MemberDashboardResponse.class))
                    )
            }
    )
    public MemberDashboardResponse getDashboard(
            @Parameter(
                    description = "회원 ID",
                    example = "101"
            )
            @PathVariable(name = "memberId") Long memberId
    ) {
        return service.getDashboardByMemberId(memberId);
    }
}
