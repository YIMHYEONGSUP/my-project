package hyeong.backend.domain.auth.details;

import hyeong.backend.domain.member.domain.persist.Member;
import hyeong.backend.domain.member.domain.persist.MemberRepository;
import hyeong.backend.domain.member.domain.vo.UserEmail;
import hyeong.backend.domain.member.error_exception.MemberNotFoundException;
import hyeong.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDetailServiceCustom implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(UserEmail.from(email))
                .map(this::createUserDetails)
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private UserDetails createUserDetails(final Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + member.getRoleType());

        return new User(member.getEmail().userEmail(),
                member.getPassword().userPassword(), Collections.singleton(grantedAuthority));
    }
}