package backend.kg_kuis_server.graduation.repository.entity;

import backend.kg_kuis_server.graduation.domain.RequirementScope;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraduationRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RequirementScope scope;       // BACHELOR or MAJOR

    @Column(length = 100)
    private String majorName;             // 전공명

    @Column(length = 200)
    private String title;                 // 표의 "요건 내용"

    private Integer criterionValue;       // 기준값

    @Column(length = 500)
    private String description;           // 비고/추가설명
}
