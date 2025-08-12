package backend.kg_kuis_server.graduation.service.dto;

public record DuplicateCourseRow(
        String courseNumber, String courseName, String category,
        Integer credit, String letterGrade
) {
}