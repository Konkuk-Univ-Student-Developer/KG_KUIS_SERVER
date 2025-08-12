package backend.kg_kuis_server.scholarship.repository;

import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.scholarship.repository.entity.ScholarshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScholarshipEntityRepository extends JpaRepository<ScholarshipEntity, Long> {

    @Query("""
        select s from ScholarshipEntity s
        where s.year = :year and s.semester = :semester
        order by s.applyStartDate asc, s.applyStartTime asc
    """)
    List<ScholarshipEntity> findAllForSemester(Integer year, Semester semester);
}
