package backend.kg_kuis_server.graduation.service;

import backend.kg_kuis_server.global.exception.CustomException;
import backend.kg_kuis_server.graduation.domain.RequirementScope;
import backend.kg_kuis_server.graduation.exception.GraduationErrorCode;
import backend.kg_kuis_server.graduation.repository.GraduationRequirementRepository;
import backend.kg_kuis_server.graduation.repository.entity.GraduationRequirement;
import backend.kg_kuis_server.graduation.service.dto.GraduationCheckResponse;
import backend.kg_kuis_server.graduation.service.dto.GraduationCheckRow;
import backend.kg_kuis_server.graduation.repository.MemberGraduationRepository;
import backend.kg_kuis_server.member.repository.MemberRepository;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import backend.kg_kuis_server.graduation.repository.entity.MemberGraduation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static backend.kg_kuis_server.member.exception.MemberErrorCode.*;

@Service
@RequiredArgsConstructor
public class GraduationService {

    private final GraduationRequirementRepository graduationRequirementRepository;
    private final MemberRepository memberRepository;
    private final MemberGraduationRepository graduationRepository;

    @Transactional(readOnly = true)
    public GraduationCheckResponse check(Long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(NOT_FOUND));

        // 학사 공통
        var bachelorReqs = graduationRequirementRepository.findByScope(RequirementScope.학사);
        var bachelorRows = bachelorReqs.stream().map(r -> toRow(member, r)).toList();

        // 전공 졸업 요건 충족
        var mainProgram = graduationRequirementRepository.findByScope(RequirementScope.전공);
        var mainProgramRows = mainProgram.stream().map(r -> toRow(member, r)).toList();

        return new GraduationCheckResponse(bachelorRows, mainProgramRows);
    }

    private GraduationCheckRow toRow(MemberEntity member, GraduationRequirement r) {
        MemberGraduation memberGraduation = graduationRepository.findMemberGraduationByMemberAndGraduationRequirement(member, r)
                .orElseThrow(() -> new CustomException(GraduationErrorCode.NOT_FOUND));

        GraduationRequirement graduationRequirement = memberGraduation.getGraduationRequirement();

        return GraduationCheckRow
                .builder()
                .critierion(graduationRequirement.getCriterionValue() + "과목")
                .title(graduationRequirement.getTitle())
                .acquired(memberGraduation.getSubject() + "과목")
                .lack(graduationRequirement.getCriterionValue() - memberGraduation.getSubject() + "학점")
                .result(graduationRequirement.getCriterionValue() - memberGraduation.getSubject() <= 0 ? "합격" : "불합")
                .detail(graduationRequirement.getDescription())
                .build();
    }
}