package backend.kg_kuis_server.chatbot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final VectorStore vectorStore;
    private final ChatClient chat;

    public ChatService(VectorStore vectorStore, ChatClient.Builder builder) {
        this.vectorStore = vectorStore;
        this.chat = builder.build();
    }

    public Answer ask(String question) {
        var request = SearchRequest.query(question).withTopK(10);
        List<Document> hits = vectorStore.similaritySearch(request);

        // 최신순 정렬 (공지=pubDate, 일정=startDate 기준)
        var topRecent = hits.stream()
                .sorted(Comparator.comparing(
                        (Document d) -> parseDate(d.getMetadata()),
                        Comparator.nullsLast(Comparator.naturalOrder())
                ).reversed())
                .limit(5)
                .toList();

        var context = topRecent.stream()
                .map(d -> {
                    var md = d.getMetadata();
                    String type = md.getOrDefault("type", "unknown").toString();
                    return switch (type) {
                        case "notice" -> """
                                - 유형: 공지
                                  제목: %s
                                  카테고리: %s
                                  일자: %s
                                  링크: %s
                                  본문 요약: %s
                                """.formatted(
                                safe(md.get("title"), ""),
                                safe(md.get("categoryName"), ""),
                                safe(md.get("pubDate"), ""),
                                safe(md.get("link"), ""),
                                summarizeLine(extractBody(d.getContent()), 2000)
                        );
                        case "schedule" -> """
                                - 유형: 학사일정
                                  제목: %s
                                  기간: %s ~ %s
                                  내용 요약: %s
                                """.formatted(
                                safe(md.get("title"), ""),
                                safe(md.get("startDate"), ""),
                                safe(md.get("endDate"), ""),
                                summarizeLine(d.getContent(), 500)
                        );
                        default -> d.getContent();
                    };
                })
                .collect(Collectors.joining("\n"));

        var nowKst = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        var content = chat.prompt()
                .system("""
                        너는 대학 학사 도우미야.
                        오늘은 %s(KST)야.
                        "공지사항"과 "학사일정" 데이터를 참고해서 질문에 답해.
                        반드시 제목/본문/날짜를 참고해서 한국어로 대답해야 해.
                        근거가 부족하면 "관련 데이터를 찾을 수 없습니다." 라고 답해.
                        답변 끝에는 참고한 데이터 제목과 url을 bullet로 정리해.
                        """.formatted(nowKst))
                .user(user -> user
                        .text("[질문]\n{q}\n\n[관련 데이터]\n{ctx}")
                        .param("q", question)
                        .param("ctx", context))
                .call()
                .content();

        return new Answer(content);
    }

    private static LocalDate parseDate(Object metadata) {
        if (metadata == null) return null;
        try {
            return LocalDate.parse(metadata.toString());
        } catch (Exception ignored) {
        }
        return null;
    }

    private static String extractBody(String content) {
        var idx = content.indexOf("[본문]");
        return idx >= 0 ? content.substring(idx + "[본문]".length()).trim() : content;
    }

    private static String summarizeLine(String s, int max) {
        var t = s.replaceAll("\\s+", " ").trim();
        return t.length() > max ? t.substring(0, max) + "…" : t;
    }

    private static String safe(Object v, String fallback) {
        return v == null ? fallback : v.toString();
    }

    public record Answer(String answer) {
    }
}
