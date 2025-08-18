package backend.kg_kuis_server.course.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lecture_plans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LecturePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sbjt_id")
    private String sbjtId;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subject_name_eng")
    private String subjectNameEng;

    @Column(columnDefinition = "text")
    private String notes;

    @Column(columnDefinition = "text")
    private String goal;

}
