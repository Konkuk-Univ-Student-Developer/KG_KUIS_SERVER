package backend.kg_kuis_server.scholarship.repository.entity;

import backend.kg_kuis_server.member.domain.Semester;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "scholarships",
        indexes = {
                @Index(name = "idx_scholarship_yr_sem", columnList = "year, semester"),
                @Index(name = "idx_scholarship_code", columnList = "scholarship_code"),
                @Index(name = "idx_apply_period", columnList = "apply_start_date, apply_end_date")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScholarshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Semester semester;

    @Column(name = "scholarship_code", nullable = false, length = 20)
    private String scholarshipCode;

    @Column(name = "scholarship_name", nullable = false, length = 200)
    private String scholarshipName;

    @Column(name = "round_no")
    private Integer roundNo;

    @Column(name = "apply_start_date", nullable = false, length = 8)
    private String applyStartDate;

    @Column(name = "apply_start_time", nullable = false, length = 4)
    private String applyStartTime;

    @Column(name = "apply_end_date", nullable = false, length = 8)
    private String applyEndDate;

    @Column(name = "apply_end_time", nullable = false, length = 4)
    private String applyEndTime;

    @Transient
    public LocalDate getApplyStartLocalDate() {
        return LocalDate.of(
                Integer.parseInt(applyStartDate.substring(0, 4)),
                Integer.parseInt(applyStartDate.substring(4, 6)),
                Integer.parseInt(applyStartDate.substring(6, 8))
        );
    }

    @Transient
    public LocalTime getApplyStartLocalTime() {
        return LocalTime.of(
                Integer.parseInt(applyStartTime.substring(0, 2)),
                Integer.parseInt(applyStartTime.substring(2, 4))
        );
    }

    @Transient
    public LocalDateTime getApplyStartAt() {
        return LocalDateTime.of(getApplyStartLocalDate(), getApplyStartLocalTime());
    }

    @Transient
    public LocalDate getApplyEndLocalDate() {
        return LocalDate.of(
                Integer.parseInt(applyEndDate.substring(0, 4)),
                Integer.parseInt(applyEndDate.substring(4, 6)),
                Integer.parseInt(applyEndDate.substring(6, 8))
        );
    }

    @Transient
    public LocalTime getApplyEndLocalTime() {
        return LocalTime.of(
                Integer.parseInt(applyEndTime.substring(0, 2)),
                Integer.parseInt(applyEndTime.substring(2, 4))
        );
    }

    @Transient
    public LocalDateTime getApplyEndAt() {
        return LocalDateTime.of(getApplyEndLocalDate(), getApplyEndLocalTime());
    }
}