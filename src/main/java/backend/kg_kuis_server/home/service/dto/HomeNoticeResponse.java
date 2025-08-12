package backend.kg_kuis_server.home.service.dto;

import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;

public record HomeNoticeResponse(
        Long id,
        String title,
        String pubDate
) {

    public static HomeNoticeResponse from(NoticeEntity entity) {
        return new HomeNoticeResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getPubDate()
        );
    }
}
