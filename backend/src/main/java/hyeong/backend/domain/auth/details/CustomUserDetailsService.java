package hyeong.backend.domain.auth.details;

import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.exceptions.MarketNotFoundException;
import hyeong.backend.domain.market.repository.MarketRepository;
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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MarketRepository marketRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return !marketRepository.findByEmail(MarketEmail.from(email)).isEmpty() ? marketRepository.findByEmail(MarketEmail.from(email))
                .map(this::createdUserDetailsMarket)
                .orElseThrow(() -> new MarketNotFoundException((ErrorCode.MARKET_NOT_FOUND))) : memberRepository.findByEmail(MemberEmail.from(email))
                .map(this::createdUserDetailsMember)
                .orElseThrow(() -> new MemberNotFoundException((ErrorCode.MEMBER_NOT_FOUND)));
    }

    private UserDetails createdUserDetailsMember(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + member.getRoleType());

        return new User(member.getEmail().email(),
                member.getPassword().password(),
                Collections.singleton(grantedAuthority));
    }

    private UserDetails createdUserDetailsMarket(Market market) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + market.getRoleType());

        return new User(market.getEmail().email(),
                market.getPassword().password(),
                Collections.singleton(grantedAuthority));
    }
}