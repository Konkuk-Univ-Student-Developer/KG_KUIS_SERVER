package backend.kg_kuis_server.course.repository;

import backend.kg_kuis_server.course.domain.SubjectClassification;
import backend.kg_kuis_server.course.repository.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectEntityRepository extends JpaRepository<SubjectEntity, Long> {
    boolean existsAllByClassification(SubjectClassification classification);

    long countByClassification(SubjectClassification classification);
}
