package backend.kg_kuis_server.scholarship.controller;

import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.scholarship.service.ScholarshipQueryService;
import backend.kg_kuis_server.scholarship.service.dto.AvailableListResponse;
import backend.kg_kuis_server.scholarship.service.dto.DisbursementHistoryResponse;
import backend.kg_kuis_server.scholarship.service.dto.MyApplicationListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/scholarships")
@RequiredArgsConstructor
public class ScholarshipController {

    private final ScholarshipQueryService scholarshipQueryService;

    // 상단: 신청 가능 목록
    @GetMapping("/{memberId}/available")
    public AvailableListResponse available(
            @PathVariable Long memberId,
            @RequestParam Integer year,
            @RequestParam Semester semester
    ) {
        return scholarshipQueryService.getAvailable(year, semester, memberId);
    }

    @GetMapping("/{memberId}/applications")
    public MyApplicationListResponse myApplications(
            @PathVariable Long memberId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return scholarshipQueryService.getMyApplications(memberId, from, to);
    }

    @GetMapping("/{memberId}/disbursements")
    public ResponseEntity<DisbursementHistoryResponse> myApplications(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(scholarshipQueryService.getHistory(memberId));
    }
}
