package backend.kg_kuis_server.graduation.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class GraduationCreditRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 원전공, 복수전공
    @Column(nullable = false, length = 50)
    private String majorType;

    // 기교, 심교, 전필+전선, 전기, 일선 등
    @Column(nullable = false, length = 50)
    private String category;

    // 기준학점
    @Column(nullable = false)
    private int requiredCredits;
}
