package backend.kg_kuis_server.course.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "assignments")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_plan_id")
    private Long lecturePlanId;

    private String title;

    @Column(name = "due_date")
    private String dueDate;

    private String method;

    @Entity @Table(name = "weekly_plans")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WeeklyPlan {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "lecture_plan_id")
        private Long lecturePlanId;

        private Integer week;
        private String period;
        private String topic;

        @Column(columnDefinition = "text")
        private String content;

        private String type;
        private String activity;
        private String instructor;

        // getters/setters
    }
}
