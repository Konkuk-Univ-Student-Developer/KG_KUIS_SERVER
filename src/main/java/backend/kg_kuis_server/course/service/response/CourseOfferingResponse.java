package backend.kg_kuis_server.course.service.response;

import backend.kg_kuis_server.course.domain.CourseCategory;
import backend.kg_kuis_server.course.repository.entity.CourseEntity;
import backend.kg_kuis_server.member.domain.Semester;

public record CourseOfferingResponse(
        Long id,
        String courseNumber,
        String courseName,
        Integer credit,
        String professor,
        String schedule,
        Integer grade,
        CourseCategory courseCategory,
        String departmentName,
        String lectureType,
        String method,
        Semester semester
) {
    public static CourseOfferingResponse from(CourseEntity e) {
        return new CourseOfferingResponse(
                e.getId(),
                e.getCourseNumber(),
                e.getCourseName(),
                e.getCredit(),
                e.getProfessor(),
                e.getSchedule(),
                e.getGrade(),
                e.getCourseCategory(),
                e.getDepartmentName(),
                e.getLectureType(),
                e.getMethod(),
                e.getSemester()
        );
    }
}