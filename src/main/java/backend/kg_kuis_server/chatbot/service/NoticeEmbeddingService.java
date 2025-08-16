package backend.kg_kuis_server.chatbot.service;

import backend.kg_kuis_server.notice.repository.NoticeRepository;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeEmbeddingService {

    private final NoticeRepository noticeRepository;
    private final VectorStore vectorStore; // 구현체는 PGVector로 자동주입

    private static final int BATCH_SIZE = 100; // 배치 크기 (필요에 따라 조절)

    /**
     * 전체 재색인 (배치 처리)
     */
    @Transactional
    public void reindexAll() {
        var notices = noticeRepository.findAll();

        // 배치 단위로 나눔
        for (int i = 0; i < notices.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, notices.size());
            var batch = notices.subList(i, end);

            List<Document> docs = batch.stream()
                    .map(n -> new Document(
                            UUID.nameUUIDFromBytes(
                                    ("notice:" + n.getId()).getBytes(StandardCharsets.UTF_8)
                            ).toString(),
                            """
                            [제목] %s
                            [카테고리] %s
                            [본문]
                            %s
                            """.formatted(n.getTitle(), n.getCategoryName(), nullToEmpty(n.getDescription())),
                            Map.of(
                                    "noticeId", n.getId(),
                                    "categoryId", n.getCategoryId(),
                                    "categoryName", n.getCategoryName(),
                                    "pubDate", n.getPubDate(),
                                    "author", nullToEmpty(n.getAuthor()),
                                    "link", nullToEmpty(n.getLink())
                            )
                    ))
                    .collect(Collectors.toList());

            vectorStore.add(docs);

            // API Rate Limit 회피를 위해 잠깐 대기
            try {
                Thread.sleep(1000); // 1초 대기
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 단건 업서트: 기존 문서 삭제 후 재삽입
     */
    @Transactional
    public void upsertOne(Long noticeId) {
        noticeRepository.findById(noticeId).ifPresent(n -> {
            String stableId = UUID.nameUUIDFromBytes(
                    ("notice:" + n.getId()).getBytes(StandardCharsets.UTF_8)
            ).toString();
            try {
                vectorStore.delete(List.of(stableId));
            } catch (Exception ignore) {
            }
            var doc = new Document(
                    stableId,
                    "[제목] %s\n[카테고리] %s\n[본문]\n%s".formatted(
                            n.getTitle(), n.getCategoryName(), nullToEmpty(n.getDescription())),
                    Map.of(
                            "noticeId", n.getId(),
                            "categoryId", n.getCategoryId(),
                            "categoryName", n.getCategoryName(),
                            "pubDate", n.getPubDate(),
                            "author", nullToEmpty(n.getAuthor()),
                            "link", nullToEmpty(n.getLink())
                    )
            );
            vectorStore.add(List.of(doc));
        });
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}
