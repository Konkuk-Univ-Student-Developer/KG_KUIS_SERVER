package backend.kg_kuis_server.member.repository.entity;

import backend.kg_kuis_server.member.domain.Semester;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberMajor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(length = 20)
    private String programType;       // "원전공", "복수전공", "부전공"

    // 전공/대학 메타
    @Column(length = 100)
    private String majorName;         // 전공명

    @Column(length = 100)
    private String collegeName;       // 대학명

    // 신청 정보
    private Integer appliedYear;      // 신청년도

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Semester appliedSemester; // 신청학기 (1학기/2학기)

    // 논문 관련
    @Column(length = 50)
    private String thesisType;        // 논문유형 (없음/일반/산업체/졸작대체 등, UI의 '음'은 '없음' 의미로 매핑 권장)

    @Column(length = 255)
    private String thesisTitle;       // 논문제목

    @Column(length = 5)
    private String passed;            // 합격(Y/N) — 필요 시 Boolean으로 변경 가능
}
