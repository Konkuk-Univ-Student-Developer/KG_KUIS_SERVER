package backend.kg_kuis_server.chatbot.service;

import backend.kg_kuis_server.notice.repository.NoticeRepository;
import backend.kg_kuis_server.schedule.repository.ScheduleRepository;
import backend.kg_kuis_server.schedule.repository.entity.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmbeddingService {

    private final NoticeRepository noticeRepository;
    private final ScheduleRepository scheduleRepository;
    private final VectorStore vectorStore;

    private static final int BATCH_SIZE = 100;

    /**
     * 전체 재색인 (공지 + 일정)
     */
    @Transactional
    public void reindexAll() {
        //reindexNotices();
        reindexSchedules();
    }

    private void reindexNotices() {
        var notices = noticeRepository.findAll();

        for (int i = 0; i < notices.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, notices.size());
            var batch = notices.subList(i, end);

            List<Document> docs = batch.stream()
                    .map(n -> {
                        String stableId = stableId("notice", n.getId());
                        return new Document(
                                stableId,
                                """
                                [제목] %s
                                [카테고리] %s
                                [본문]
                                %s
                                """.formatted(n.getTitle(), n.getCategoryName(), nullToEmpty(n.getDescription())),
                                Map.of(
                                        "type", "notice",
                                        "noticeId", n.getId(),
                                        "categoryId", n.getCategoryId(),
                                        "categoryName", n.getCategoryName(),
                                        "pubDate", n.getPubDate(),
                                        "author", nullToEmpty(n.getAuthor()),
                                        "link", nullToEmpty(n.getLink()),
                                        "title", n.getTitle()
                                )
                        );
                    })
                    .collect(Collectors.toList());

            vectorStore.add(docs);
        }
    }

    private void reindexSchedules() {
        var schedules = scheduleRepository.findAll();

        for (int i = 0; i < schedules.size(); i += BATCH_SIZE) {
            int end = Math.min(i + BATCH_SIZE, schedules.size());
            var batch = schedules.subList(i, end);

            List<Document> docs = batch.stream()
                    .map(this::toScheduleDocument)
                    .collect(Collectors.toList());

            vectorStore.add(docs);
        }
    }

    private Document toScheduleDocument(ScheduleEntity s) {
        String stableId = stableId("schedule", s.getId());
        String content = """
                [일정] %s
                [기간] %s%s
                """.formatted(
                s.getTitle(),
                s.getStartDate().format(DateTimeFormatter.ISO_DATE),
                (s.getEndDate() != null && !s.getStartDate().equals(s.getEndDate()))
                        ? " ~ " + s.getEndDate().format(DateTimeFormatter.ISO_DATE)
                        : ""
        );

        return new Document(
                stableId,
                content,
                Map.of(
                        "type", "schedule",
                        "scheduleId", s.getId(),
                        "title", s.getTitle(),
                        "startDate", s.getStartDate().toString(),
                        "endDate", s.getEndDate().toString()
                )
        );
    }

    private static String stableId(String prefix, Long id) {
        return UUID.nameUUIDFromBytes((prefix + ":" + id).getBytes(StandardCharsets.UTF_8)).toString();
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}
