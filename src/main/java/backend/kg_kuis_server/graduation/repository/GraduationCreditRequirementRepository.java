package backend.kg_kuis_server.graduation.repository;

import backend.kg_kuis_server.graduation.repository.entity.GraduationCreditRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduationCreditRequirementRepository extends JpaRepository<GraduationCreditRequirement, Long> {
}
