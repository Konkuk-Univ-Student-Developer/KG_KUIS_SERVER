package backend.kg_kuis_server.member.repository.entity;

import backend.kg_kuis_server.member.domain.Gender;
import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.member.domain.StudentStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String studentNo;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String nameEn;

    @Column(name = "birth_raw", length = 10)
    private String birthRaw;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @Column(length = 100)
    private String college;

    @Column(length = 100)
    private String department;

    private Integer entranceYear;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Semester entranceSemester;

    @Column(length = 50)
    private String admissionType;

    @Column(length = 100)
    private String selectionType;

    private Integer curriculumYear;

    @Column(length = 50)
    private String studentType;

    private Integer grade;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
