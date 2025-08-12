package backend.kg_kuis_server.scholarship.service.dto;

import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.scholarship.repository.entity.ScholarshipDisbursement;

public record DisbursementRow(
        String semesterText,      // "1학기" / "2학기" / "여름학기" / "겨울학기"
        String scholarshipName,   // 장학금명
        Long admissionAmount,     // 장학입학금 (원 단위, 정수)
        Long tuitionAmount,       // 장학수업료
        Long flatAmount,          // 정액금액
        String paidOn             // 지급일 "M/d" 포맷 (예: "7/13")
) {
    public static DisbursementRow from(ScholarshipDisbursement d) {
        var date = d.getPaymentDate();
        String paid = (date == null) ? null : (date.getMonthValue() + "/" + date.getDayOfMonth());
        return new DisbursementRow(
                toKo(d.getSemester()),
                d.getScholarshipName(),
                toLong(d.getAdmissionAmount()),
                toLong(d.getTuitionAmount()),
                toLong(d.getFlatAmount()),
                paid
        );
    }

    private static Long toLong(java.math.BigDecimal v) {
        return v == null ? 0L : v.longValue();
    }

    private static String toKo(Semester s) {
        return switch (s) {
            case FIRST -> "1학기";
            case SUMMER -> "여름학기";
            case SECOND -> "2학기";
            case WINTER -> "겨울학기";
        };
    }
}