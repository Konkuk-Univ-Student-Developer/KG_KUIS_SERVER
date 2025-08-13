package backend.kg_kuis_server.scholarship.service.dto;

import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.scholarship.repository.entity.MemberScholarShip;

public record MyApplicationItemResponse(
        Integer applicationYear,
        Semester applicationSemester, // "1학기" / "2학기" ...
        String scholarshipName,
        String appliedDate,         // "2025.05.04"
        String status,              // "선정" / "탈락" / "접수" 등
        String rejectionReason      // 탈락사유 (없으면 null)
) {
    public static MyApplicationItemResponse from(MemberScholarShip a) {
        return new MyApplicationItemResponse(
                a.getScholarship().getAcademicYear(),
                a.getScholarship().getSemester(),
                a.getScholarship().getScholarshipName(),
                a.getApplicationDate().toString().replace('-', '.'),
                a.getApplicationStatus(),
                a.getRejectionReason()
        );
    }
}
