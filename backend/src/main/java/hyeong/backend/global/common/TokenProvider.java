package hyeong.backend.global.common;

import hyeong.backend.domain.market.entity.persist.Market;
import hyeong.backend.domain.market.entity.vo.MarketEmail;
import hyeong.backend.domain.market.exceptions.MarketNotFoundException;
import hyeong.backend.domain.market.repository.MarketRepository;
import hyeong.backend.domain.member.Repository.MemberRepository;
import hyeong.backend.domain.member.entity.persist.Member;
import hyeong.backend.domain.member.entity.vo.MemberEmail;
import hyeong.backend.domain.member.exceptions.MemberNotFoundException;
import hyeong.backend.global.errors.exceptions.ErrorCode;
import hyeong.backend.global.jwt.exceptions.UnAuthorizationException;
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
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    private final RedisService redisService;
    private final MemberRepository memberRepository;

    private final MarketRepository marketRepository;

    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.accessToken-validity-in-seconds}") long accessTokenValidityInMilliseconds,
            @Value("${jwt.refreshToken-validity-in-seconds}") long refreshTokenValidityInMilliseconds,
            MemberRepository memberRepository,
            RedisService redisService, MarketRepository marketRepository) {
        this.secret = secret;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds * 1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds * 1000;
        this.memberRepository = memberRepository;
        this.redisService = redisService;
        this.marketRepository = marketRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDTO createTokenMember(String email, Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        Date accessTokenEXP = new Date(now + accessTokenValidityInMilliseconds);
        Date refreshTokenEXP = new Date(now + refreshTokenValidityInMilliseconds);

        long accessTokenEXPTime = accessTokenEXP.getTime();
        long refreshTokenEXPTime = refreshTokenEXP.getTime();


        Member member = memberRepository.findByEmail(MemberEmail.from(email)).orElseThrow(() -> {
            throw new MemberNotFoundException(ErrorCode.USER_NOT_FOUND);
        });

        String accessToken = Jwts.builder()
                .claim(AUTHORITIES_KEY, authorities)
                .claim("email", member.getEmail().email())
                .claim("nickname", member.getNickname().nickname())
                .setExpiration(accessTokenEXP)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .claim(AUTHORITIES_KEY, authorities)
                .claim("email", member.getEmail().email())
                .claim("nickname", member.getNickname().nickname())
                .setExpiration(refreshTokenEXP)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        redisService.setRefreshToken(refreshToken , refreshTokenEXPTime);

        return TokenDTO.create(AccessToken.from(accessToken), RefreshToken.from(refreshToken));
    }
 public TokenDTO createTokenMarket(String email, Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        Date accessTokenEXP = new Date(now + accessTokenValidityInMilliseconds);
        Date refreshTokenEXP = new Date(now + refreshTokenValidityInMilliseconds);

        long accessTokenEXPTime = accessTokenEXP.getTime();
        long refreshTokenEXPTime = refreshTokenEXP.getTime();

        log.info("expire time = " + now + " " + refreshTokenEXPTime);

     Market market = marketRepository.findByEmail(MarketEmail.from(email)).orElseThrow(() -> {
         throw new MarketNotFoundException(ErrorCode.MARKET_NOT_FOUND);
     });

        String accessToken = Jwts.builder()
                .claim(AUTHORITIES_KEY, authorities)
                .claim("email", market.getEmail().email())
                .claim("name", market.getName().marketName())
                .setExpiration(accessTokenEXP)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        String refreshToken = Jwts.builder()
                .claim(AUTHORITIES_KEY, authorities)
                .claim("email", market.getEmail().email())
                .claim("name", market.getName().marketName())
                .setExpiration(refreshTokenEXP)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        redisService.setRefreshToken(refreshToken , refreshTokenEXPTime);

        return TokenDTO.create(AccessToken.from(accessToken), RefreshToken.from(refreshToken));
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        List<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(String.valueOf(claims.get("email")), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            if (redisService.getBlacklist(token) != null) {
                throw new UnAuthorizationException(ErrorCode.TOKEN_HAS_BLACKLIST);
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
