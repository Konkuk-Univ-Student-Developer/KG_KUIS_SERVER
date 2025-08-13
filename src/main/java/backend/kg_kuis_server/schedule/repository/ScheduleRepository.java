package backend.kg_kuis_server.schedule.repository;

import backend.kg_kuis_server.schedule.repository.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findTop3ByEndDateGreaterThanEqualOrderByStartDateAscEndDateAsc(LocalDate today);

}
