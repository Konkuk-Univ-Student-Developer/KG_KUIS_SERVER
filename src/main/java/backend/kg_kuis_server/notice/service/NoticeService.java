package backend.kg_kuis_server.notice.service;

import backend.kg_kuis_server.notice.repository.NoticeRepository;
import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;
import backend.kg_kuis_server.notice.repository.impl.NoticeQueryRepositoryImpl;
import backend.kg_kuis_server.notice.service.dto.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeQueryRepositoryImpl noticeQueryRepository;
    private final NoticeFavoriteService noticeFavoriteService;

    /**
     * 공지사항 조회 (카테고리 + 키워드 + 북마크 상단 고정)
     */
    @Transactional(readOnly = true)
    @Cacheable(
            value = "notices",
            key = "#categoryId + ':' + (#keyword != null ? #keyword : 'all') + ':' + #pageable.pageNumber"
    )
    public backend.kg_kuis_server.notice.dto.PageResponse<NoticeResponse> getNotices(Integer categoryId, String keyword, Pageable pageable) {
        Page<NoticeEntity> page;

        // 조건 분기
        if (keyword != null && !keyword.isBlank()) {
            page = noticeQueryRepository.searchByTitleAndCategory(keyword, categoryId, pageable);
        } else if (categoryId != null) {
            page = noticeRepository.findByCategoryId(categoryId, pageable);
        } else {
            page = noticeRepository.findAll(pageable);
        }

        // 현재 페이지 Notice ID들 추출
        List<Long> ids = page.getContent().stream()
                .map(NoticeEntity::getId)
                .toList();

        // 즐겨찾기된 ID 조회
        var favIds = noticeFavoriteService.favoriteIdsForCurrentUser(ids);

        // NoticeEntity → NoticeResponse 변환 + 북마크 정렬
        List<NoticeResponse> content = page.getContent().stream()
                .map(e -> NoticeResponse.from(e, favIds.contains(e.getId())))
                .sorted(
                        Comparator
                                .comparing(NoticeResponse::isBookMarked).reversed() // 북마크 true 먼저
                                .thenComparing(NoticeResponse::pubDate, Comparator.reverseOrder()) // pubDate 최신순
                )
                .toList();

        return new backend.kg_kuis_server.notice.dto.PageResponse<NoticeResponse>(content, page.getNumber(), page.getSize(), page.getTotalElements());
    }

    /**
     * 공지사항 등록/수정/삭제 시 캐시 전체 삭제
     */
    @CacheEvict(value = "notices", allEntries = true)
    public void clearNoticeCache() {
    }
}
