package backend.kg_kuis_server.member.service.dto;

import java.util.List;

public record MemberDashboardResponse(
        Basic basic,
        Summary summary,
        List<ProgramInfo> programs
) {
    public record Basic(
            String studentNo, String name, String birthRaw, String gender,
            Integer entranceYear, String entranceSemester, String admissionType,
            String college, String department, Integer grade, String status, String studentType
    ) {
    }

    public record Summary(
            Integer totalEarnedCredits, Integer requiredCredits,
            String gpa, String gpaScale, String passStatus, Boolean earlyGraduation
    ) {
    }

    public record ProgramInfo(
            String programType, String majorName, String collegeName,
            Integer appliedYear, String appliedSemester,
            String thesisType, String thesisTitle, String passed
    ) {
    }
}