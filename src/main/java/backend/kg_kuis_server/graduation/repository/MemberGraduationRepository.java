package backend.kg_kuis_server.graduation.repository;

import backend.kg_kuis_server.graduation.repository.entity.GraduationRequirement;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import backend.kg_kuis_server.graduation.repository.entity.MemberGraduation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberGraduationRepository extends JpaRepository<MemberGraduation, Long> {
    Optional<MemberGraduation> findMemberGraduationByMemberAndGraduationRequirement(MemberEntity member, GraduationRequirement r);
}
