package backend.kg_kuis_server.notice.repository;

import backend.kg_kuis_server.notice.repository.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    Page<NoticeEntity> findByCategoryId(Integer categoryId, Pageable pageable);

    Page<NoticeEntity> findAllByOrderByPubDateDesc(Pageable pageable);
}
