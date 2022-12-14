package hyeong.backend.domain.auth.service;

import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.entity.vo.MarketPassword;
import hyeong.backend.global.common.AccessToken;
import hyeong.backend.global.common.RefreshToken;
import hyeong.backend.global.common.TokenDTO;
import hyeong.backend.global.common.TokenProvider;
import hyeong.backend.global.errors.exceptions.ErrorCode;
import hyeong.backend.global.jwt.exceptions.TokenNotFoundException;
import hyeong.backend.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
public class MarketAuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder managerBuilder;
    private final RedisService redisService;

    @Value("${jwt.refreshToken-validity-in-seconds}")
    private long refreshTokenValidityInMilliseconds;

    public TokenDTO login (final MarketEmail marketEmail, final MarketPassword marketPassword) {

        final String email = marketEmail.email();
        final String password = marketPassword.password();

        log.info("email = {}" , email);
        log.info("password = {}" , password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        log.info("authentication Token = {}" , authenticationToken.getPrincipal().toString());

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.debug("authentication.principal : {}", authentication.getPrincipal());
        log.debug("authentication.authorities : {}", authentication.getAuthorities());

        return tokenProvider.createTokenMarket(authentication.getName(), authentication);

    }

    public TokenDTO reissue(AccessToken accessToken, RefreshToken refreshToken) {

        log.info("access check = {}" , accessToken);
        log.info("refresh check = {}" , refreshToken);

        if (!tokenProvider.validateToken(accessToken.getAccessToken())) {
            throw new TokenNotFoundException(ErrorCode.Token_NOT_FOUND);
        }

        Authentication authentication = tokenProvider.getAuthentication(accessToken.getAccessToken());

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createTokenMarket(principal.getUsername(), authentication);
    }

    public void logout(RefreshToken refreshToken, AccessToken accessToken) {

        redisService.setBlackList(accessToken.getAccessToken(), 3600);

    }
}
