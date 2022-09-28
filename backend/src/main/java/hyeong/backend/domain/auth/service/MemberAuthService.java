package hyeong.backend.domain.auth.service;

import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.entity.vo.MemberPassword;
import hyeong.backend.global.common.AccessToken;
import hyeong.backend.global.common.RefreshToken;
import hyeong.backend.global.common.TokenDTO;
import hyeong.backend.global.common.TokenProvider;
import hyeong.backend.global.jwt.exceptions.TokenNotFoundException;
import hyeong.backend.global.errors.exceptions.ErrorCode;
import hyeong.backend.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberAuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder managerBuilder;
    private final RedisService redisService;

    @Value("${jwt.refreshToken-validity-in-seconds}")
    private long refreshTokenValidityInMilliseconds;

    public TokenDTO login (final MemberEmail memberEmail, final MemberPassword memberPassword) {
        final String email = memberEmail.email();
        final String password = memberPassword.password();

        log.info("email = {}" , email);
        log.info("password = {}" , password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 문제인 부분 'cause duplicated of 'custom user details service'
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.debug("authentication.principal : {}", authentication.getPrincipal());
        log.debug("authentication.authorities : {}", authentication.getAuthorities());

        return tokenProvider.createTokenMember(authentication.getName(), authentication);

    }

    public TokenDTO reissue( AccessToken accessToken, RefreshToken refreshToken) {

        log.info("access check = {}" , accessToken);
        log.info("refresh check = {}" , refreshToken);

        if (!tokenProvider.validateToken(accessToken.getAccessToken())) {
            throw new TokenNotFoundException(ErrorCode.Token_NOT_FOUND);
        }

        Authentication authentication = tokenProvider.getAuthentication(accessToken.getAccessToken());

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createTokenMember(principal.getUsername(), authentication);
    }

    public void logout(RefreshToken refreshToken, AccessToken accessToken) {

        redisService.setBlackList(accessToken.getAccessToken(), 3600);

    }



}
