package hyeong.backend.global.common;

import hyeong.backend.domain.member.domain.persist.Member;
import hyeong.backend.domain.member.domain.persist.MemberRepository;
import hyeong.backend.domain.member.domain.vo.UserEmail;
import hyeong.backend.domain.member.error_exception.MemberNotFoundException;
import hyeong.backend.global.error.exception.ErrorCode;
import hyeong.backend.global.jwt.error.UnAuthorizationException;
import hyeong.backend.global.redis.RedisService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "role";

    private final String secret;
    private final long accesTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    private final RedisService redisService;
    private final MemberRepository memberRepository;

    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.accessToken-validity-in-seconds}") long accesTokenValidityInMilliseconds,
            @Value("${jwt.refreshToken-validity-in-seconds}") long refreshTokenValidityInMilliseconds,
            MemberRepository memberRepository,
            RedisService redisService
    ){
        this.secret = secret;
        this.accesTokenValidityInMilliseconds = accesTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
        this.memberRepository = memberRepository;
        this.redisService = redisService;
    }


    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    public TokenDTO createToken(String email, Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining("m"));

        log.debug("authorities : {}" , authorities);

        long now = (new Date()).getTime();

        Member member = memberRepository.findByEmail(UserEmail.from(email)).orElseThrow(()-> {
            throw new MemberNotFoundException(ErrorCode.USER_NOT_FOUND);
        });

        String accessToken = Jwts.builder()
                .claim("email", member.getEmail().userEmail())
                .claim("nickname", member.getNickname().userNickname())
                .claim(AUTHORITIES_KEY, authorities)
                .setExpiration(new Date(now + accesTokenValidityInMilliseconds))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .claim(AUTHORITIES_KEY, authorities)
                .claim("email", member.getEmail().userEmail())
                .claim("nickname", member.getNickname().userNickname())
                .setExpiration(new Date(now + refreshTokenValidityInMilliseconds))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        AccessToken newAccessToken = AccessToken.from(accessToken);
        RefreshToken newRefreshToken = RefreshToken.from(refreshToken);

        return TokenDTO.create(newAccessToken, newRefreshToken);

    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        List<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(String.valueOf(claims.get("email")), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validationToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            if (redisService.getBlackList(token) != null) {
                throw new UnAuthorizationException(ErrorCode.UN_AUTHORIZATION_ERROR);
            }

            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }

        return false;
    }

}