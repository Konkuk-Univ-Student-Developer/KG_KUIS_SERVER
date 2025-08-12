package backend.kg_kuis_server.notice.repository;

import backend.kg_kuis_server.notice.repository.entity.NoticeFavoriteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface NoticeFavoriteRepository extends JpaRepository<NoticeFavoriteEntity, Long> {
    boolean existsByMemberIdAndNoticeId(Long userId, Long noticeId);

    Optional<NoticeFavoriteEntity> findByMemberIdAndNoticeId(Long userId, Long noticeId);

    void deleteByMemberIdAndNoticeId(Long userId, Long noticeId);

    Page<NoticeFavoriteEntity> findByMemberId(Long userId, Pageable pageable);

    Page<NoticeFavoriteEntity> findByMemberIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    List<NoticeFavoriteEntity> findByMemberIdAndNoticeIdIn(Long userId, Collection<Long> noticeIds);
}
