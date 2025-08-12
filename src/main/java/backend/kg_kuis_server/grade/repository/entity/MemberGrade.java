package backend.kg_kuis_server.grade.repository.entity;

import backend.kg_kuis_server.course.repository.entity.CourseEntity;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MemberGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(length = 5)
    private String letterGrade;

    private Integer attendance;    // 출석
    private Integer midterm;       // 중간고사
    private Integer finalExam;     // 기말고사
    private Integer assignment;    // 과제물
    private Integer project;       // 프로젝트
    private Integer quiz;          // 퀴즈
    private Integer presentation;  // 발표
    private Integer discussion;    // 토론
    private Integer etc5;          // 기타5
}