package backend.kg_kuis_server.grade.service.response;

import lombok.Builder;

@Builder
public record GradeItem(
        int no,               // 표 Nox
        String courseCode,    // 학수번호 (COAA8723)
        String classNo,       // 과목번호/분반 (1114)
        String courseName,    // 과목명
        String instructor,    // 담당교수
        int credit,           // 학점(3)
        String division,      // 이수구분(전선)
        String letterGrade,   // 등급(A+)
        String gradingMethod, // 성적평가방법(상대평가)
        GradeDetailResponse gradeDetailResponse // 상세 성적
) {
}
