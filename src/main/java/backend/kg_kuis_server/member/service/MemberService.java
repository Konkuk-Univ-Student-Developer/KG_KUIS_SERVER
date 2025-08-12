package backend.kg_kuis_server.member.service;

import backend.kg_kuis_server.global.exception.CustomException;
import backend.kg_kuis_server.member.exception.MemberErrorCode;
import backend.kg_kuis_server.member.repository.MemberRepository;
import backend.kg_kuis_server.member.repository.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Long getSingleMemberId() {
        return getSingleUser().getId();
    }

    public MemberEntity getSingleUser() {
        return memberRepository.findTopByOrderByIdAsc()
                .orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND));
    }
}
