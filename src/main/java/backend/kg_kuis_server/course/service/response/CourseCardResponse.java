package backend.kg_kuis_server.course.service.response;

import backend.kg_kuis_server.course.domain.CourseCategory;
import backend.kg_kuis_server.course.repository.entity.CourseEntity;
import backend.kg_kuis_server.member.domain.Semester;

public record CourseCardResponse(Long id, String courseCode, String courseName, String professor, String schedule,
                                 String courseNumber, CourseCategory courseCategory, Integer grade,
                                 String departmentName, String method, Integer credit, String lectureType,
                                 Semester semester) {

    public static CourseCardResponse from(CourseEntity e) {
        return new CourseCardResponse(e.getId(), e.getCourseCode(), e.getCourseName(), e.getProfessor(), e.getSchedule(), e.getCourseNumber(), e.getCourseCategory(), e.getGrade(), e.getDepartmentName(), e.getMethod(), e.getCredit(), e.getLectureType(), e.getSemester());
    }
}
