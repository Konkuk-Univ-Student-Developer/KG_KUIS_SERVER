package backend.kg_kuis_server.course.service;

import backend.kg_kuis_server.course.domain.CourseCategory;
import backend.kg_kuis_server.course.repository.CourseRepositoryImpl;
import backend.kg_kuis_server.course.repository.entity.CourseEntity;
import backend.kg_kuis_server.course.service.response.CourseCardResponse;
import backend.kg_kuis_server.course.service.response.CourseOfferingResponse;
import backend.kg_kuis_server.member.domain.Semester;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepositoryImpl repository;

    public Page<CourseOfferingResponse> search(Integer year,
                                               Semester semester,
                                               CourseCategory category,
                                               String professor,
                                               String courseCode,
                                               String departmentName,
                                               String courseName,
                                               Pageable pageable) {
        Page<CourseEntity> page = repository.search(
                year, semester, category, professor, courseCode, departmentName, courseName, pageable
        );
        return page.map(CourseOfferingResponse::from);
    }

    public Page<CourseCardResponse> searchCard(Integer year,
                                               Semester semester,
                                               CourseCategory category,
                                               String professor,
                                               String courseCode,
                                               String departmentName,
                                               String courseName,
                                               Pageable pageable) {
        Page<CourseEntity> page = repository.search(
                year, semester, category, professor, courseCode, departmentName, courseName, pageable
        );
        return page.map(CourseCardResponse::from);
    }
}