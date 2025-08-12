package backend.kg_kuis_server.course.repository;

import backend.kg_kuis_server.course.repository.entity.CourseEntity;
import backend.kg_kuis_server.course.repository.entity.QCourseEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryImpl {

    private final JPAQueryFactory query;

    public Page<CourseEntity> search(Integer year,
                                     String semester,
                                     String category,
                                     String professor,
                                     String courseCode,
                                     String departmentName,
                                     Pageable pageable) {
        QCourseEntity c = QCourseEntity.courseEntity;
        BooleanBuilder where = new BooleanBuilder();

        if (year != null) where.and(c.year.eq(year));
        if (semester != null && !semester.isBlank()) where.and(c.semester.eq(semester));
        if (category != null && !category.isBlank()) where.and(c.category.eq(category));
        if (professor != null && !professor.isBlank()) where.and(c.professor.containsIgnoreCase(professor));
        if (courseCode != null && !courseCode.isBlank()) where.and(c.courseCode.eq(courseCode));
        if (departmentName != null && !departmentName.isBlank())
            where.and(c.departmentName.containsIgnoreCase(departmentName));

        // 목록 조회
        List<CourseEntity> content = query
                .selectFrom(c)
                .where(where)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(c.id.desc()) // 정렬 기본값, 필요시 pageable.getSort() 처리
                .fetch();

        // 전체 카운트
        long total = query
                .select(c.id.count())
                .from(c)
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
