package hyeong.backend.domain.auth.details;

import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.exceptions.MemberNotFoundException;
import hyeong.backend.global.errors.exceptions.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomMemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(MemberEmail.from(email))
                .map(this::createdUserDetails)
                .orElseThrow(() -> new MemberNotFoundException((ErrorCode.MEMBER_NOT_FOUND)));
    }

    private UserDetails createdUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + member.getRoleType());

        return new User(member.getEmail().email(),
                member.getPassword().password(),
                Collections.singleton(grantedAuthority));

    }
}
