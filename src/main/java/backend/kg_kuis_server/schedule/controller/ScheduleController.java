package backend.kg_kuis_server.schedule.controller;

import backend.kg_kuis_server.schedule.service.ScheduleService;
import backend.kg_kuis_server.schedule.service.response.ScheduleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calendars")
@RequiredArgsConstructor
@Tag(name = "학사 일정 API", description = "학사 일정 조회 관련 API")
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping
    @Operation(
            summary = "다가오는 일정 3개 조회",
            description = "오늘 기준으로 이미 지난 일정은 제외하고, 가장 가까운 날짜 순으로 3개의 일정을 반환합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ScheduleResponse.class))
                    )
            }
    )
    public List<ScheduleResponse> getScheduleThree() {
        return service.nextThree();
    }
}
