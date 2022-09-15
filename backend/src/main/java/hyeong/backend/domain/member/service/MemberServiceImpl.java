package hyeong.backend.domain.member.service;

import hyeong.backend.domain.market.entity.vo.MarketName;
import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.dto.MemberJoinRequestDTO;
import hyeong.backend.domain.member.dto.MemberJoinResponseDTO;
import hyeong.backend.domain.member.dto.MemberResponseDTO;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.exceptions.DuplicateEmailException;
import hyeong.backend.domain.member.exceptions.MemberNotFoundException;
import hyeong.backend.global.common.TokenDTO;
import hyeong.backend.global.common.TokenProvider;
import hyeong.backend.global.errors.exceptions.ErrorCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder managerBuilder;

    @Override
    public MemberJoinResponseDTO create(Member member) {

        member.encode(encoder);

        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new DuplicateEmailException(ErrorCode.EMAIL_DUPLICATION);
        }
        return MemberJoinResponseDTO.from(memberRepository.save(member));
    }

    @Override
    public MemberResponseDTO findByEmail(MemberEmail email) {
        Member findMember = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND);
        });
        return MemberResponseDTO.create(findMember);
    }

    @Override
    public TokenDTO update(Member member, MemberEmail email) {
        Member findMember = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND);
        });

        Member updatedMember = findMember.update(member, encoder);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(updatedMember.getEmail().email(), member.getPassword().password());

        Authentication authentication = managerBuilder.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(updatedMember.getEmail().email(), authentication);

    }

    public void delete(final MemberEmail email) {
        memberRepository.deleteByEmail(email);
    }
}
