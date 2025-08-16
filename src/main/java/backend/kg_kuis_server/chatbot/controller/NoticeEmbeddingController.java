//package backend.kg_kuis_server.chatbot.controller;
//
//import backend.kg_kuis_server.chatbot.service.NoticeEmbeddingService;
//import io.swagger.v3.oas.annotations.Hidden;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@Hidden // Swagger 문서에 표시되지 않음
//@RestController
//@RequestMapping("/internal/embeddings/notices")
//@RequiredArgsConstructor
//public class NoticeEmbeddingController {
//
//    private final NoticeEmbeddingService noticeEmbeddingService;
//
//    /**
//     * 전체 재색인
//     */
//    @PostMapping("/reindex")
//    public String reindexAll() {
//        noticeEmbeddingService.reindexAll();
//        return "전체 재색인 완료";
//    }
//
//    /**
//     * 단건 업서트
//     */
//    @PostMapping("/{noticeId}/upsert")
//    public String upsertOne(@PathVariable Long noticeId) {
//        noticeEmbeddingService.upsertOne(noticeId);
//        return "공지사항 " + noticeId + " 업서트 완료";
//    }
//}
//
