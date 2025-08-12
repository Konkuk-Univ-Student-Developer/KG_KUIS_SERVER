package backend.kg_kuis_server.course.service;

import backend.kg_kuis_server.course.repository.CourseRepositoryImpl;
import backend.kg_kuis_server.course.repository.entity.CourseEntity;
import backend.kg_kuis_server.course.service.response.CourseOfferingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepositoryImpl repository;

    public Page<CourseOfferingResponse> search(Integer year,
                                               String semester,
                                               String category,
                                               String professor,
                                               String courseCode,
                                               String departmentName,
                                               Pageable pageable) {
        Page<CourseEntity> page = repository.search(
                year, semester, category, professor, courseCode, departmentName, pageable
        );
        return page.map(CourseOfferingResponse::from);
    }
}