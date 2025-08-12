package backend.kg_kuis_server.notice.service.dto;

import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;

public record NoticeResponse(
        Long id,
        Integer categoryId,
        String categoryName,
        String title,
        String link,
        String pubDate,
        String author,
        String description,
        boolean isBookMarked
) {
    public static NoticeResponse from(NoticeEntity noticeEntity, boolean isBookMarked) {
        return new NoticeResponse(
                noticeEntity.getId(),
                noticeEntity.getCategoryId(),
                noticeEntity.getCategoryName(),
                noticeEntity.getTitle(),
                noticeEntity.getLink(),
                noticeEntity.getPubDate(),
                noticeEntity.getAuthor(),
                noticeEntity.getDescription(),
                isBookMarked
        );
    }
}
