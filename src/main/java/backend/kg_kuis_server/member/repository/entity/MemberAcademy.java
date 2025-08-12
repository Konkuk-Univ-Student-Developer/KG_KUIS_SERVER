package backend.kg_kuis_server.member.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberAcademy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    private Integer totalEarnedCredits;   // 총 취득 학점

    private Integer requiredCredits;      // 졸업 필요 학점

    private BigDecimal gpa;               // 전체 평점

    private BigDecimal gpaScale;          // 스케일

    // 합격/진행 상태 요약
    @Column(length = 20)
    private String passStatus;

    private Boolean earlyGraduation;      // 조기졸업 여부 (Y/N)

    private Boolean honor; // 우등
}
