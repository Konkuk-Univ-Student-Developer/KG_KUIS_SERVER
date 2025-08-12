package backend.kg_kuis_server.scholarship.service.dto;

import backend.kg_kuis_server.scholarship.repository.entity.ScholarshipEntity;

public record AvailableScholarshipResponse(
        Long scholarshipId,
        String actionLabel,         // "신청"
        String name,                // 장학금명
        String periodText,          // "07-09 14:00 ~ 07-15 10:00"
        String noticeUrl,           // 공지사항 링크(없으면 null)
        boolean hasAttachment,      // 첨부파일 존재 여부
        String myApplicationStatus  // 내 신청상태 (예: "신청완료", "미신청" 등)
) {
    public static AvailableScholarshipResponse of(
            ScholarshipEntity s, String myStatus, String noticeUrl, boolean hasAttachment
    ) {
        String period = toPeriodText(s);
        return new AvailableScholarshipResponse(
                s.getId(),
                "신청",
                s.getScholarshipName(),
                period,
                noticeUrl,
                hasAttachment,
                myStatus
        );
    }

    private static String toPeriodText(ScholarshipEntity s) {
        var start = s.getApplyStartAt();
        var end = s.getApplyEndAt();
        return String.format("%02d-%02d %02d:%02d ~ %02d-%02d %02d:%02d",
                start.getMonthValue(), start.getDayOfMonth(), start.getHour(), start.getMinute(),
                end.getMonthValue(), end.getDayOfMonth(), end.getHour(), end.getMinute()
        );
    }
}
