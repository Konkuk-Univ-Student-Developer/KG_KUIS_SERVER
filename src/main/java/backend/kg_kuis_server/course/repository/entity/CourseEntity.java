package backend.kg_kuis_server.course.repository.entity;

import backend.kg_kuis_server.course.domain.CourseCategory;
import backend.kg_kuis_server.member.domain.Semester;
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
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "course_year")
    private Integer courseYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Column(nullable = false)
    private Integer grade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseCategory courseCategory;

    @Column(nullable = false, name = "course_code")
    private String courseCode;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private Integer credit;

    @Column(nullable = false)
    private String departmentName;

    @Column(nullable = false)
    private String schedule;

    @Column(nullable = false)
    private String professor;

    @Column(nullable = false, name = "course_number")
    private String courseNumber;

    @Column(nullable = false)
    private String lectureType;

    private String method;
}
