package backend.kg_kuis_server.notice.service.dto;

import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;

public record NoticeSimpleResponse(
        Long id,
        String title,
        String pubDate,
        boolean bookmarked
) {

    public static NoticeSimpleResponse from(NoticeEntity entity, boolean bookmarked) {
        return new NoticeSimpleResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getPubDate(),
                bookmarked
        );
    }
}

