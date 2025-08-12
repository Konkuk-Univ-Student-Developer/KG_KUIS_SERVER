package backend.kg_kuis_server.graduation.repository.entity;

import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class MemberGraduation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToOne
    @JoinColumn(name = "graduation_id")
    private GraduationRequirement graduationRequirement;

    private Integer score;
}
