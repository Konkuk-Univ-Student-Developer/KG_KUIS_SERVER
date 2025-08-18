package backend.kg_kuis_server.course.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "evaluation_items")
@Getter
@Setter
public class EvaluationItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_plan_id")
    private Long lecturePlanId;

    @Column(name = "item_name")
    private String itemName;

    private Integer ratio;

    @Column(name = "full_score")
    private Integer fullScore;

    @Column(name = "is_public")
    private String isPublic;

}
