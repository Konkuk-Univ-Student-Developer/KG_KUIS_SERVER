package backend.kg_kuis_server.chatbot.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortalMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;     // 예: 학적, 학적변동관리, 전공진입관리
    private String title;        // 예: "조기졸업신청"
    private String description;  // 예: "학적 > 조기졸업신청 메뉴에서 신청 가능"
    private String path;         // 예: "학적 > 조기졸업신청"
}
