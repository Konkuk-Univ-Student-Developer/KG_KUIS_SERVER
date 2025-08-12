package backend.kg_kuis_server.notice.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notices")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer categoryId;

    private String categoryName;

    private String title;

    private String link;

    private String pubDate;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String description;
}
