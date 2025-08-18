package backend.kg_kuis_server.course.repository;

import backend.kg_kuis_server.course.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLecturePlanIdOrderById(Long lecturePlanId);
}