package backend.kg_kuis_server.grade.service.response;

import backend.kg_kuis_server.grade.repository.entity.MemberGrade;

public record GradeDetailResponse(
        Integer attendance,
        Integer midterm,
        Integer finalExam,
        Integer assignment,
        Integer project,
        Integer quiz,
        Integer presentation,
        Integer discussion,
        Integer etc5
) {
    public static GradeDetailResponse from(MemberGrade mg) {
        return new GradeDetailResponse(
                mg.getAttendance(),
                mg.getMidterm(),
                mg.getFinalExam(),
                mg.getAssignment(),
                mg.getProject(),
                mg.getQuiz(),
                mg.getPresentation(),
                mg.getDiscussion(),
                mg.getEtc5()
        );
    }
}
