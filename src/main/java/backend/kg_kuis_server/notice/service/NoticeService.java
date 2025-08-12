package backend.kg_kuis_server.notice.service;

import backend.kg_kuis_server.notice.repository.impl.NoticeQueryRepositoryImpl;
import backend.kg_kuis_server.notice.repository.NoticeRepository;
import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;
import backend.kg_kuis_server.notice.service.dto.NoticeResponse;
import backend.kg_kuis_server.notice.service.dto.NoticeSimpleResponse;
import lombok.RequiredArgsConstructor;
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

    @Transactional(readOnly = true)
    public Page<NoticeResponse> findByCategory(Integer categoryId, Pageable pageable) {
        Page<NoticeEntity> page = noticeRepository.findByCategoryId(categoryId, pageable);

        List<Long> ids = page.getContent().stream().map(NoticeEntity::getId).toList();

        var favIds = noticeFavoriteService.favoriteIdsForCurrentUser(ids);

        return page.map(e -> NoticeResponse.from(e, favIds.contains(e.getId())));
    }


    public Page<NoticeSimpleResponse> searchNotices(String keyword, Integer categoryId, Pageable pageable) {
        Page<NoticeEntity> page = noticeQueryRepository.searchByTitleAndCategory(keyword, categoryId, pageable);

        List<Long> ids = page.getContent().stream().map(NoticeEntity::getId).toList();

        var favIds = noticeFavoriteService.favoriteIdsForCurrentUser(ids);

        return page.map(e -> NoticeSimpleResponse.from(e, favIds.contains(e.getId())));
    }
}
