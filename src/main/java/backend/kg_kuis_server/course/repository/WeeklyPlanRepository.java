package backend.kg_kuis_server.course.repository;// src/main/java/com/example/lecture/repo/WeeklyPlanRepository.java

import backend.kg_kuis_server.course.domain.WeeklyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WeeklyPlanRepository extends JpaRepository<WeeklyPlan, Long> {
    List<WeeklyPlan> findByLecturePlanIdOrderByWeekAscIdAsc(Long lecturePlanId);
}
