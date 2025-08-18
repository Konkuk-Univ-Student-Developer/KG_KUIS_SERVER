package backend.kg_kuis_server.course.domain;// src/main/java/com/example/lecture/domain/backend.kg_kuis_server.course.domain.Book.java

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lecture_plan_id")
    private Long lecturePlanId;

    private String type;
    private String title;
    private String author;
    private String publisher;
    private String year;

}
