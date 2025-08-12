package backend.kg_kuis_server.scholarship.repository;

import backend.kg_kuis_server.scholarship.repository.entity.ScholarshipDisbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScholarshipDisbursementRepository
        extends JpaRepository<ScholarshipDisbursement, Long> {

    @Query("""
        select d from ScholarshipDisbursement d
        where d.member.id = :memberId
        order by d.year asc, d.semester asc, d.paymentDate asc, d.id asc
    """)
    List<ScholarshipDisbursement> findAllByStudent(Long memberId);
}
