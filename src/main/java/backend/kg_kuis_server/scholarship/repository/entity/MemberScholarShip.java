package backend.kg_kuis_server.scholarship.repository.entity;

import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "scholarship_applications",
        indexes = {
                @Index(name = "idx_student_year_sem", columnList = "member_id"),
        }
)
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberScholarShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scholarship_id")
    private ScholarshipEntity scholarship;

    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;

    @Column(name = "application_status", nullable = false, length = 20)
    private String applicationStatus;

    @Column(name = "rejection_reason", length = 255)
    private String rejectionReason;
}
