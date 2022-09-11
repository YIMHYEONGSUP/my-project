package hyeong.backend.domain.member.service;

import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public MemberJoinResponseDTO save(Member member) {
        return MemberJoinResponseDTO.from(memberRepository.save(member));
    }

    @Override
    public MemberResponseDTO findByEmail(MemberEmail email) {
        return MemberResponseDTO.from(memberRepository.findByEmail(email));
    }
}
