package backend.kg_kuis_server.graduation.repository;

import backend.kg_kuis_server.graduation.domain.RequirementScope;
import backend.kg_kuis_server.graduation.repository.entity.GraduationRequirement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraduationRequirementRepository extends JpaRepository<GraduationRequirement, Long> {
    List<GraduationRequirement> findByScope(RequirementScope scope);

    List<GraduationRequirement> findByScopeAndMajorName(RequirementScope scope, String majorName);
}
