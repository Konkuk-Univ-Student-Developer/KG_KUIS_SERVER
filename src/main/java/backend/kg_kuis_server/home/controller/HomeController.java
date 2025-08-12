package backend.kg_kuis_server.home.controller;

import backend.kg_kuis_server.home.service.HomeService;
import backend.kg_kuis_server.home.service.dto.HomeResponse;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
@Tag(name = "Home", description = "홈 화면 API")
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    @Operation(
            summary = "메인 화면 데이터 조회",
            description = """
            홈 화면에 필요한 데이터를 조회합니다.
            """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공",
                            content = @Content(schema = @Schema(implementation = HomeResponse.class))
                    )
            }
    )
    public ResponseEntity<HomeResponse> getHomeData() {
        return ResponseEntity.ok(homeService.getHomeData());
    }
}
