package backend.kg_kuis_server.course.repository;

import backend.kg_kuis_server.course.repository.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
