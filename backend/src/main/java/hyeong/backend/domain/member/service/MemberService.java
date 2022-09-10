package hyeong.backend.domain.member.service;

import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    MemberJoinResponseDTO save(Member member);

    MemberResponseDTO findByEmail(MemberEmail email);
}
