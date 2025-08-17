package backend.kg_kuis_server.chatbot.domain;

import backend.kg_kuis_server.scholarship.repository.entity.ScholarshipEntity;
import org.springframework.ai.document.Document;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

public class ScholarshipMapper {

    public static Document toDocument(ScholarshipEntity s) {
        String stableId = UUID.nameUUIDFromBytes(("scholarship:" + s.getId()).getBytes(StandardCharsets.UTF_8)).toString();

        String content = """
                [장학금명] %s
                [학년도/학기] %d년 %s
                [차수] %s
                [신청기간] %s %s ~ %s %s
                [공지사항]
                %s
                """.formatted(
                s.getScholarshipName(),
                s.getAcademicYear(), s.getSemester(),
                s.getRoundNo() == null ? "-" : s.getRoundNo(),
                s.getApplyStartDate(), s.getApplyStartTime(),
                s.getApplyEndDate(), s.getApplyEndTime(),
                s.getNotice() == null ? "" : s.getNotice()
        );

        return new Document(
                stableId,
                content,
                Map.of(
                        "type", "scholarship",
                        "scholarshipId", s.getId(),
                        "academicYear", s.getAcademicYear(),
                        "semester", s.getSemester().name(),
                        "scholarshipCode", s.getScholarshipCode(),
                        "scholarshipName", s.getScholarshipName(),
                        "roundNo", s.getRoundNo() == null ? "" : s.getRoundNo().toString(),
                        "applyStartAt", s.getApplyStartAt().toString(),
                        "applyEndAt", s.getApplyEndAt().toString()
                )
        );
    }
}
