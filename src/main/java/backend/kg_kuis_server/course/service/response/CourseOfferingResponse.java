package backend.kg_kuis_server.course.service.response;

import backend.kg_kuis_server.course.repository.entity.CourseEntity;

public record CourseOfferingResponse(
        Long id,
        Integer grade,
        String courseCode,
        String courseName,
        Integer credit,
        String professor,
        String schedule
) {
    public static CourseOfferingResponse from(CourseEntity e) {
        return new CourseOfferingResponse(
                e.getId(),
                e.getGrade(),
                e.getCourseCode(),
                e.getCourseName(),
                e.getCredit(),
                e.getProfessor(),
                e.getSchedule()
        );
    }
}