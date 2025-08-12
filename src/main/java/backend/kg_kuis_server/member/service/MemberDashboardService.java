package backend.kg_kuis_server.member.service;

import backend.kg_kuis_server.global.exception.CustomException;
import backend.kg_kuis_server.member.repository.MemberAcademicRepository;
import backend.kg_kuis_server.member.repository.MemberMajorRepository;
import backend.kg_kuis_server.member.repository.MemberRepository;
import backend.kg_kuis_server.member.repository.entity.MemberAcademy;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import backend.kg_kuis_server.member.repository.entity.MemberMajor;
import backend.kg_kuis_server.member.service.dto.MemberDashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static backend.kg_kuis_server.member.exception.MemberErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberDashboardService {

    private final MemberRepository memberRepository;
    private final MemberAcademicRepository summaryRepository;
    private final MemberMajorRepository programRepository;

    @Transactional(readOnly = true)
    public MemberDashboardResponse getDashboardByMemberId(Long memberId) {
        MemberEntity m = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(NOT_FOUND));

        MemberAcademy sum = summaryRepository.findByMember(m).orElse(null);
        List<MemberMajor> programs = programRepository.findByMemberOrderByIdAsc(m);

        return toResponse(m, sum, programs);
    }

    private MemberDashboardResponse toResponse(MemberEntity m, MemberAcademy s, List<MemberMajor> ps) {
        var basic = new MemberDashboardResponse.Basic(
                m.getStudentNo(),
                m.getName(),
                m.getBirthRaw(),
                m.getGender() != null ? m.getGender().name() : null,
                m.getEntranceYear(),
                m.getEntranceSemester() != null ? m.getEntranceSemester().name() : null,
                m.getAdmissionType(),
                m.getCollege(),
                m.getDepartment(),
                m.getGrade(),
                m.getStatus() != null ? m.getStatus().name() : null,
                m.getStudentType()
        );

        var summary = (s == null) ? null
                : new MemberDashboardResponse.Summary(
                s.getTotalEarnedCredits(),
                s.getRequiredCredits(),
                s.getGpa() != null ? s.getGpa().toPlainString() : null,
                s.getGpaScale() != null ? s.getGpaScale().toPlainString() : null,
                s.getPassStatus(),
                s.getEarlyGraduation()
        );

        var programs = ps.stream().map(p ->
                new MemberDashboardResponse.ProgramInfo(
                        p.getProgramType(),
                        p.getMajorName(),
                        p.getCollegeName(),
                        p.getAppliedYear(),
                        p.getAppliedSemester() != null ? p.getAppliedSemester().name() : null,
                        p.getThesisType(),
                        p.getThesisTitle(),
                        p.getPassed()
                )).toList();

        return new MemberDashboardResponse(basic, summary, programs);
    }
}
