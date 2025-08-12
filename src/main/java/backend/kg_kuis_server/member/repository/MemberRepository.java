package backend.kg_kuis_server.member.repository;

import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findTopByOrderByIdAsc();
}
