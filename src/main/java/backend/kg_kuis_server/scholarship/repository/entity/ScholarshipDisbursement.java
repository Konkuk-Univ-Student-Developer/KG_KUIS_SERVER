package backend.kg_kuis_server.scholarship.repository.entity;

import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "scholarship_disbursements",
        indexes = {
                @Index(name = "idx_disb_student", columnList = "member_id"),
                @Index(name = "idx_disb_year_sem", columnList = "year, semester"),
                @Index(name = "idx_disb_paydate", columnList = "payment_date")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScholarshipDisbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester", nullable = false, length = 10)
    private Semester semester;

    @Column(name = "scholarship_name", nullable = false, length = 200)
    private String scholarshipName;

    @Column(name = "admission_amount", precision = 15, scale = 2)
    private BigDecimal admissionAmount;

    @Column(name = "tuition_amount", precision = 15, scale = 2)
    private BigDecimal tuitionAmount;

    @Column(name = "flat_amount", precision = 15, scale = 2)
    private BigDecimal flatAmount;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    public static ScholarshipDisbursement ofRaw(
            MemberEntity member, int year, Semester semester, String name,
            Long admission, Long tuition, Long flat, String yyyymmdd
    ) {
        return ScholarshipDisbursement.builder()
                .member(member)
                .year(year)
                .semester(semester)
                .scholarshipName(name)
                .admissionAmount(admission == null ? null : new BigDecimal(admission))
                .tuitionAmount(tuition == null ? null : new BigDecimal(tuition))
                .flatAmount(flat == null ? null : new BigDecimal(flat))
                .paymentDate(LocalDate.of(
                        Integer.parseInt(yyyymmdd.substring(0, 4)),
                        Integer.parseInt(yyyymmdd.substring(4, 6)),
                        Integer.parseInt(yyyymmdd.substring(6, 8))))
                .build();
    }
}