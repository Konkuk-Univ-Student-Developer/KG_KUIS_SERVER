package backend.kg_kuis_server.member.controller;

import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import backend.kg_kuis_server.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "Members", description = "회원 관련 API")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    @Operation(
            summary = "현재 로그인한 회원 정보 조회",
            description = """
            현재 인증된 회원의 상세 정보를 반환합니다.
            """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "회원 정보 조회 성공",
                            content = @Content(schema = @Schema(implementation = MemberEntity.class))
                    )
            }
    )
    public ResponseEntity<MemberEntity> me() {
        return ResponseEntity.ok(memberService.getSingleUser());
    }
}