package backend.kg_kuis_server.chatbot.repository;

import backend.kg_kuis_server.chatbot.repository.entity.PortalMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalMenuRepository extends JpaRepository<PortalMenu, Long> {
}

