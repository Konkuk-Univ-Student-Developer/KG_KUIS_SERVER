package backend.kg_kuis_server.member.repository;

import backend.kg_kuis_server.member.repository.entity.MemberAcademy;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberAcademicRepository extends JpaRepository<MemberAcademy, Long> {
    Optional<MemberAcademy> findByMember(MemberEntity member);
}
