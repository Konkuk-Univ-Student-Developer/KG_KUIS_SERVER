package backend.kg_kuis_server.notice.service;

import backend.kg_kuis_server.notice.dto.PageResponse;
import backend.kg_kuis_server.notice.repository.impl.NoticeQueryRepositoryImpl;
import backend.kg_kuis_server.notice.repository.NoticeRepository;
import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;
import backend.kg_kuis_server.notice.service.dto.NoticeResponse;
import backend.kg_kuis_server.notice.service.dto.NoticeSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeQueryRepositoryImpl noticeQueryRepository;
    private final NoticeFavoriteService noticeFavoriteService;

    /**
     * 카테고리별 공지사항 조회 (즐겨찾기 여부 포함, Redis 캐싱)
     */
    @Transactional(readOnly = true)
    @Cacheable(
            value = "noticeByCategory",
            key = "#categoryId != null ? #categoryId + ':' + #pageable.pageNumber : 'all:' + #pageable.pageNumber"
    )
    public PageResponse<NoticeResponse> findByCategory(Integer categoryId, Pageable pageable) {
        Page<NoticeEntity> page;

        if (categoryId == null) {
            page = noticeRepository.findAllByOrderByPubDateDesc(pageable);
        } else {
            page = noticeRepository.findByCategoryId(categoryId, pageable);
        }

        return mapWithFavoritesToPageResponse(page);
    }

    /**
     * Page<NoticeEntity> → PageResponse<NoticeResponse> 변환
     */
    private PageResponse<NoticeResponse> mapWithFavoritesToPageResponse(Page<NoticeEntity> page) {
        List<Long> ids = page.getContent().stream()
                .map(NoticeEntity::getId)
                .toList();

        var favIds = noticeFavoriteService.favoriteIdsForCurrentUser(ids);

        List<NoticeResponse> content = page.getContent().stream()
                .map(e -> NoticeResponse.from(e, favIds.contains(e.getId())))
                .toList();

        return new PageResponse<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
    }

    /**
     * 공지사항 등록/수정/삭제 시 캐시 전체 삭제
     */
    @CacheEvict(value = "noticeByCategory", allEntries = true)
    public void clearNoticeCache() {
    }

    public Page<NoticeSimpleResponse> searchNotices(String keyword, Integer categoryId, Pageable pageable) {
        Page<NoticeEntity> page = noticeQueryRepository.searchByTitleAndCategory(keyword, categoryId, pageable);

        List<Long> ids = page.getContent().stream().map(NoticeEntity::getId).toList();

        var favIds = noticeFavoriteService.favoriteIdsForCurrentUser(ids);

        return page.map(e -> NoticeSimpleResponse.from(e, favIds.contains(e.getId())));
    }
}
