package backend.kg_kuis_server.course.repository;

import backend.kg_kuis_server.course.domain.EvaluationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationItemRepository extends JpaRepository<EvaluationItem, Long> {
    List<EvaluationItem> findByLecturePlanIdOrderById(Long lecturePlanId);
}
