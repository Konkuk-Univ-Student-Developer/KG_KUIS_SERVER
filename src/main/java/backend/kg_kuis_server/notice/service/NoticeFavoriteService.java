package backend.kg_kuis_server.notice.service;

import backend.kg_kuis_server.global.exception.CustomException;
import backend.kg_kuis_server.member.exception.MemberErrorCode;
import backend.kg_kuis_server.member.service.MemberService;
import backend.kg_kuis_server.notice.exception.NoticeErrorCode;
import backend.kg_kuis_server.notice.repository.NoticeFavoriteRepository;
import backend.kg_kuis_server.notice.repository.NoticeRepository;
import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;
import backend.kg_kuis_server.notice.repository.entity.NoticeFavoriteEntity;
import backend.kg_kuis_server.notice.service.dto.NoticeSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeFavoriteService {

    private final NoticeFavoriteRepository favoriteRepository;
    private final NoticeRepository noticeRepository;
    private final MemberService memberService;

    public void mark(Long noticeId) {
        Long memberId = memberService.getSingleMemberId();

        if (!noticeRepository.existsById(noticeId)) {
            throw new CustomException(MemberErrorCode.NOT_FOUND);
        }
        if (favoriteRepository.existsByMemberIdAndNoticeId(memberId, noticeId)) {
            throw new CustomException(NoticeErrorCode.FAVORITE_ALREADY_EXISTS);
        }
        favoriteRepository.save(
                NoticeFavoriteEntity.builder()
                        .memberId(memberId)
                        .noticeId(noticeId)
                        .build()
        );
    }

    public void unmark(Long noticeId) {
        Long memberId = memberService.getSingleMemberId();
        favoriteRepository.deleteByMemberIdAndNoticeId(memberId, noticeId);
    }

    @Transactional(readOnly = true)
    public Page<NoticeSimpleResponse> getMyBookmarks(Pageable pageable) {
        Long memberId = memberService.getSingleMemberId();

        Page<NoticeFavoriteEntity> favPage =
                favoriteRepository.findByMemberIdOrderByCreatedAtDesc(memberId, pageable);

        List<Long> ids = favPage.map(NoticeFavoriteEntity::getNoticeId).getContent();
        if (ids.isEmpty()) return new PageImpl<>(List.of(), pageable, favPage.getTotalElements());

        List<NoticeEntity> notices = noticeRepository.findAllById(ids);

        Map<Long, NoticeEntity> map = notices.stream()
                .collect(Collectors.toMap(NoticeEntity::getId, n -> n));
        List<NoticeEntity> ordered = ids.stream()
                .map(map::get).filter(Objects::nonNull).toList();

        return new PageImpl<>(ordered, pageable, favPage.getTotalElements())
                .map(e -> NoticeSimpleResponse.from(e, true));
    }

    @Transactional(readOnly = true)
    public Set<Long> favoriteIdsForCurrentUser(Collection<Long> noticeIds) {
        if (noticeIds == null || noticeIds.isEmpty()) return Set.of();

        Long userId = memberService.getSingleMemberId();

        return favoriteRepository.findByMemberIdAndNoticeIdIn(userId, noticeIds)
                .stream()
                .map(NoticeFavoriteEntity::getNoticeId)
                .collect(Collectors.toSet());
    }
}
