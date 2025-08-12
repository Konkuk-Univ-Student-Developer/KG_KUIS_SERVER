package backend.kg_kuis_server.grade.service.response;

import backend.kg_kuis_server.member.domain.Semester;

import java.util.List;

public record SemesterBlock(
        Integer year,
        Semester semester,          // e.g. FIRST, SECOND
        SemesterSummary summary,    // 상단 박스
        List<SemesterCourseRow> courses // 표
) {}
