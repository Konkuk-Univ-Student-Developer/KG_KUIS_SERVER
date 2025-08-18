// src/main/java/com/example/lecture/repo/LecturePlanRepository.java
package backend.kg_kuis_server.course.repository;

import backend.kg_kuis_server.course.domain.LecturePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LecturePlanRepository extends JpaRepository<LecturePlan, Long> {
    // subject_code 기준 최신 1건
    Optional<LecturePlan> findTopBySbjtIdOrderByIdDesc(String sbjtId);
}
