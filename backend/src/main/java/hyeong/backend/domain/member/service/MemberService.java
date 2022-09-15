package hyeong.backend.domain.member.service;

import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.global.common.TokenDTO;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    MemberJoinResponseDTO create(Member member);

    MemberResponseDTO findByEmail(MemberEmail email);

    TokenDTO update(final Member member, final MemberEmail email);

    void delete(final MemberEmail email);

}
