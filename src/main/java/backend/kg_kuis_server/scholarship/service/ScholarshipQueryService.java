package backend.kg_kuis_server.scholarship.service;

import backend.kg_kuis_server.member.domain.Semester;
import backend.kg_kuis_server.scholarship.repository.MemberScholarshipRepository;
import backend.kg_kuis_server.scholarship.repository.ScholarshipDisbursementRepository;
import backend.kg_kuis_server.scholarship.repository.ScholarshipEntityRepository;
import backend.kg_kuis_server.scholarship.repository.entity.ScholarshipDisbursement;
import backend.kg_kuis_server.scholarship.repository.entity.ScholarshipEntity;
import backend.kg_kuis_server.scholarship.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScholarshipQueryService {

    private final ScholarshipEntityRepository scholarshipRepository;
    private final MemberScholarshipRepository applicationRepository;
    private final ScholarshipDisbursementRepository scholarshipDisbursementRepository;


    public AvailableListResponse getAvailable(Integer year, Semester semester, Long memberId) {
        List<ScholarshipEntity> postings = scholarshipRepository.findAllForSemester(year, semester);

        List<AvailableScholarshipResponse> items = postings.stream()
                .map(s -> {
                    // 이미 신청했는지 조회(가장 최신 건 하나)
                    var apps = applicationRepository.findLatestForPosting(
                            memberId, year, semester, s.getScholarshipName());
                    String myStatus = apps.isEmpty() ? "미신청" : "신청";

                    String noticeUrl = null;
                    boolean hasAttachment = false;

                    return AvailableScholarshipResponse.of(s, myStatus, noticeUrl, hasAttachment);
                })
                .toList();

        return new AvailableListResponse(year, semester, items);
    }

    // 하단: 신청 이력(기본 최근 1년)
    public MyApplicationListResponse getMyApplications(Long memberId, LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            to = LocalDate.now();
            from = to.minusYears(1);
        }
        List<MyApplicationItemResponse> items = applicationRepository
                .findAllByStudentAndPeriod(memberId, from, to)
                .stream()
                .map(MyApplicationItemResponse::from)
                .toList();

        return new MyApplicationListResponse(items);
    }

    public DisbursementHistoryResponse getHistory(Long memberId) {
        List<ScholarshipDisbursement> rows = scholarshipDisbursementRepository.findAllByStudent(memberId);

        Map<Integer, List<ScholarshipDisbursement>> grouped =
                rows.stream().collect(Collectors.groupingBy(ScholarshipDisbursement::getYear));

        Comparator<Map.Entry<Integer, List<ScholarshipDisbursement>>> yearCmp =
                Map.Entry.<Integer, List<ScholarshipDisbursement>>comparingByKey().reversed();

        List<YearBlock> blocks = grouped.entrySet().stream()
                .sorted(yearCmp)
                .map(e -> {
                    Integer year = e.getKey();
                    Comparator<ScholarshipDisbursement> semCmp =
                            Comparator.comparingInt((ScholarshipDisbursement d) -> d.getSemester().getSemester())
                                    .reversed();

                    List<DisbursementRow> items = e.getValue().stream()
                            .sorted(semCmp.thenComparing(ScholarshipDisbursement::getPaymentDate,
                                    Comparator.nullsLast(Comparator.naturalOrder())))
                            .map(DisbursementRow::from)
                            .toList();
                    return new YearBlock(year, items);
                })
                .toList();

        return new DisbursementHistoryResponse(blocks);
    }

}