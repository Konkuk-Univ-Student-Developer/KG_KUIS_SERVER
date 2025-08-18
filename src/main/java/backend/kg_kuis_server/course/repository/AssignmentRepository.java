package backend.kg_kuis_server.course.repository;

import backend.kg_kuis_server.course.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByLecturePlanIdOrderById(Long lecturePlanId);
}