package backend.kg_kuis_server.grade.service.response;

import backend.kg_kuis_server.grade.repository.entity.MemberGrade;

// 표의 한 행
public record SemesterCourseRow(
        String classification, // 이수구분(전선/전필/교선 등)
        String courseNumber,   // 학수번호
        String courseName,     // 과목명
        Integer credit,        // 학점
        String letterGrade,    // 등급(A+ 등)
        String recognitionType, // 인정구분(옵션)
        String deletionType,    // 삭제구분(옵션)
        String deletionDate     // 삭제일자(yyyy-MM-dd, 옵션)
) {
    public static SemesterCourseRow from(MemberGrade mg) {
        var c = mg.getCourse();
        return new SemesterCourseRow(
                c.getCourseCategory().name(),
                c.getCourseNumber(),
                c.getCourseName(),
                c.getCredit(),
                mg.getLetterGrade(),
                null, // 없으면 null 처리
                null,    // 없으면 null 처리
                null     // 없으면 null 처리(문자/포맷 맞추기)
        );
    }
}
