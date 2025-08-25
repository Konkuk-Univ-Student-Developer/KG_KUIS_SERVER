package backend.kg_kuis_server.chatbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

        var topHits = hits.stream()
                .limit(5)
                .toList();

        var context = topHits.stream()
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
                        case "scholarship" -> """
                                - 유형: 장학금
                                  장학금명: %s
                                  학년도/학기: %s년 %s
                                  신청기간: %s ~ %s
                                  차수: %s
                                  공지 요약: %s
                                """.formatted(
                                safe(md.get("scholarshipName"), ""),
                                safe(md.get("academicYear"), ""),
                                safe(md.get("semester"), ""),
                                safe(md.get("applyStartAt"), ""),
                                safe(md.get("applyEndAt"), ""),
                                safe(md.get("roundNo"), ""),
                                summarizeLine(extractBody(d.getContent()), 500)
                        );
                        default -> d.getContent();
                    };
                })
                .collect(Collectors.joining("\n"));

        var nowKst = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        var today = nowKst.toLocalDate().toString();

        var systemPrompt = """
                너는 건국대학교 학사 도우미야. 시간 기준은 반드시 KST를 사용해.
                오늘은 {NOW_KST} (KST)다. 오늘 날짜는 {TODAY}다.
                현재 기준은 2025년 2학기 시작 전이다.
                
                ## 작업
                - 아래로 전달되는 "관련 데이터"는 공지사항/학사일정/장학금 문서들이다.
                - 각 문서에는 메타데이터가 포함될 수 있다:
                  - 공지: title, categoryName, pubDate, link
                  - 일정: title, startDate, endDate
                  - 장학: scholarshipName, academicYear, semester, applyStartAt, applyEndAt, roundNo
                - 네 임무는 사용자의 질문 의도를 해석하고, **유형별 시간 필터링**을 적용해 답을 간결한 한국어로 제공하는 것이다.
                - **링크가 없으면 절대 URL을 만들어내지 마라.**
                
                ## 필터링 규칙
                (코드에서 미리 필터링되지 않았다면 아래 규칙을 네가 적용해라.)
                최대한 오늘 날짜 기준 최신 데이터를 가져와야 한다.
                
                1) 장학금(scholarship)
                  - 기본: 진행중 또는 14일 이내 시작만 포함
                  - 현재 기준으로 과거 요청 시: applyEndAt < 오늘
                  - “전부/전체/모두/다” 요청 시: 제한 없이 모두
                
                2) 학사일정(schedule)
                  - 기본: 진행중 또는 90일 이내 시작만 포함
                    - 진행중: startDate ≤ 오늘 ≤ endDate
                    - 다가옴: 오늘 < startDate ≤ 오늘+90일
                  - 지난 일정 요청 시: endDate < 오늘
                  - “전부/전체/모두/다” 요청 시: 제한 없이 모두
                
                3) 공지(notice)
                  - 검색 시 제목과 본문 사용, 본문이 없으면 제목만 사용해도 괜찮음.
                  - 시간제약형(마감/접수/모집): pubDate 적용
                    - 기본: pubDate가 오늘 기준, 한달 이내 데이터 우선 적용.
                    - 기본 없으면 제한 없이 모두
                  - 상시/안내형(마감정보 없음): 제한 없이 모두
                  - “전부/전체/모두/다” 요청 시: 제한 없이 모두
                
                ## 출력 형식
                - 절대날짜(YYYY-MM-DD)와 요일(월~일)을 함께 표기.
                - 본문은 6줄 이내로 간결히. 불필요한 수사는 금지.
                - 답변 끝에 **[참고 링크]** 섹션을 추가해 bullet로 정리:
                  - `- 제목 (URL)` 형식, URL이 없으면 `- 제목` 만.
                  - 컨텍스트에 실제로 존재하는 링크만 사용(허구 금지).
                  - 중복(제목+URL 동일)은 1개만 남겨라.
                -  관련 데이터를 찾지 못하면 웹에서 검색해서 답변해줘.
                
                ## 금칙
                - 링크/날짜를 추측하거나 생성하지 마라.
                - 근거가 불충분하면 완곡하게 돌리지 말고 위 에러 문구로 단답하라.
                """
                .replace("{NOW_KST}", nowKst.toString())
                .replace("{TODAY}", today);

        var content = chat.prompt()
                .system(systemPrompt)
                .user(user -> user
                        .text("[질문]\n{q}\n\n[관련 데이터]\n{ctx}")
                        .param("q", question)
                        .param("ctx", context))
                .call()
                .content();

        return new Answer(content);
    }

    private static String extractBody(String content) {
        var idx = content.indexOf("[공지사항]");
        if (idx >= 0) return content.substring(idx + "[공지사항]".length()).trim();

        idx = content.indexOf("[본문]");
        if (idx >= 0) return content.substring(idx + "[본문]".length()).trim();

        return content;
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
