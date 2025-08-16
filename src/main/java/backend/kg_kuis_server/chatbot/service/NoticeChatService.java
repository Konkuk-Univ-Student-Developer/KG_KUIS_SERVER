package backend.kg_kuis_server.chatbot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeChatService {

    private final VectorStore vectorStore;
    private final ChatClient chat;

    public NoticeChatService(VectorStore vectorStore, ChatClient.Builder builder) {
        this.vectorStore = vectorStore;
        this.chat = builder.build();
    }

    public Answer ask(String question) {
        var request = SearchRequest.query(question).withTopK(10);
        List<Document> hits = vectorStore.similaritySearch(request);

        var topRecent = hits.stream()
                .sorted(Comparator.comparing(
                        (Document d) -> parseDateTime(d.getMetadata().get("pubDate")), // LocalDateTime
                        Comparator.nullsLast(Comparator.naturalOrder())
                ).reversed()) // 최신이 먼저
                .limit(3)
                .toList();

        // 3) 컨텍스트 생성
        var context = topRecent.stream()
                .map(d -> {
                    var md = d.getMetadata();
                    return """
                            - 제목: %s
                              카테고리: %s
                              일자: %s
                              링크: %s
                              본문 요약: %s
                            """.formatted(
                            safe(md.get("title"), extractTitle(d.getContent())),
                            safe(md.get("categoryName"), ""),
                            safe(md.get("pubDate"), ""),
                            safe(md.get("link"), ""),
                            summarizeLine(extractBody(d.getContent()), 5000)
                    );
                })
                .collect(Collectors.joining("\n"));

        // 4) 프롬프트
        var nowKst = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        var content = chat.prompt()
                .system("""
                        너는 대학 공지사항 도우미야.
                        오늘은 %s(KST)야.
                        오늘 기준 날짜가 최근 데이터를 우선 고려해.
                        주어진 "관련 공지"의 **제목과 본문**을 반드시 참고해서 한국어로 대답해.
                        답변에는 본문에서 확인된 날짜, 장소, 대상 등의 핵심 정보가 반드시 포함되어야 한다.
                        근거가 부족하면 "관련 공지를 찾을 수 없습니다." 라고 답해.
                        답변 끝에는 참고한 공지 제목/링크를 bullet로 나열해.
                        """.formatted(nowKst))
                .user(user -> user
                        .text("[질문]\n{q}\n\n[관련 공지]\n{ctx}")
                        .param("q", question)
                        .param("ctx", context))
                .call()
                .content();

        return new Answer(content);
    }

    // --- 날짜 파싱 유틸: "2020-04-09 17:25:56.0" 등 다양한 케이스 방어 ---
    private static LocalDateTime parseDateTime(Object v) {
        if (v == null) return null;
        String s = v.toString().trim();
        // 숫자면 epoch millis로 처리
        if (s.matches("\\d+")) {
            try {
                long epoch = Long.parseLong(s);
                return LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(epoch), ZoneId.of("Asia/Seoul"));
            } catch (Exception ignored) {}
        }
        // 여러 패턴 시도
        String[] patterns = new String[] {
                "yyyy-MM-dd HH:mm:ss.SSS",
                "yyyy-MM-dd HH:mm:ss.S",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd"
        };
        for (String p : patterns) {
            try {
                return LocalDateTime.parse(s.contains(".") ? s.substring(0, s.lastIndexOf('.')) + s.substring(s.lastIndexOf('.')) : s,
                        DateTimeFormatter.ofPattern(p));
            } catch (Exception ignored) {}
        }
        // 마지막 시도: 소수점 잘라내고 파싱
        try {
            if (s.contains(".")) {
                String noFrac = s.substring(0, s.indexOf('.'));
                return LocalDateTime.parse(noFrac, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        } catch (Exception ignored) {}
        return null;
    }

    // 본문만 뽑아내기
    private static String extractBody(String content) {
        var idx = content.indexOf("[본문]");
        if (idx >= 0) return content.substring(idx + "[본문]".length()).trim();
        return content;
    }

    // 제목만 추출하기
    private static String extractTitle(String content) {
        var m = java.util.regex.Pattern.compile("\\[제목]\\s*(.*)").matcher(content);
        return m.find() ? m.group(1).trim() : content.split("\\R", 2)[0];
    }

    // 긴 문자열을 잘라내는 유틸
    private static String summarizeLine(String s, int max) {
        var t = s.replaceAll("\\s+", " ").trim();
        return t.length() > max ? t.substring(0, max) + "…" : t;
    }

    private static String safe(Object v, String fallback) {
        return v == null ? fallback : v.toString();
    }

    public record Answer(String answer) {}
}
