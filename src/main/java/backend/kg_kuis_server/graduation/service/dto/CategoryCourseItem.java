package backend.kg_kuis_server.graduation.service.dto;

import backend.kg_kuis_server.course.domain.SubjectClassification;
import backend.kg_kuis_server.member.domain.Semester;

public record CategoryCourseItem(
        Integer courseYear,        // ⚠ 예약어 피해서 courseYear 컬럼 사용
        Semester semester,
        Integer gradeLevel,
        String courseNumber,
        String courseName,
        SubjectClassification divisionLabel,      // 분류(SW/취창업 등 필요 시)
        Integer credit,
        String letterGrade
) {
}