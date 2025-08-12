package backend.kg_kuis_server.member.repository;

import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import backend.kg_kuis_server.member.repository.entity.MemberMajor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMajorRepository extends JpaRepository<MemberMajor, Long> {
    List<MemberMajor> findByMemberOrderByIdAsc(MemberEntity member);
}
