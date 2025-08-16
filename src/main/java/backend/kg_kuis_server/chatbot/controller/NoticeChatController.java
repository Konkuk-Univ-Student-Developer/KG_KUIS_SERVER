package backend.kg_kuis_server.chatbot.controller;

import backend.kg_kuis_server.chatbot.service.NoticeChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ai")
@Tag(name = "공지사항 챗봇 API", description = "공지사항 데이터를 기반으로 질문에 답변하는 챗봇 API")
public class NoticeChatController {

    private final NoticeChatService chatService;

    /**
     * 질문 → 답변
     */
    @Operation(
            summary = "공지사항 챗봇 질문 API",
            description = "공지사항 데이터(제목, 본문, 카테고리 등)를 기반으로 질문에 답변을 생성합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "질문에 대한 답변 성공",
                    content = @Content(schema = @Schema(implementation = NoticeChatService.Answer.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/chat")
    public NoticeChatService.Answer chat(
            @Parameter(description = "사용자가 입력한 질문", example = "이번주 장학금 신청 일정 알려줘")
            @RequestParam String question
    ) {
        return chatService.ask(question);
    }

}
