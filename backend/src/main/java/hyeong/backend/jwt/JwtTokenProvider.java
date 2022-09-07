package hyeong.backend.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class JwtTokenProvider {

    private static final String JWT_SECRET = "secretKey";
    private static final String AUTHORITIES_KEY = "auth";
    private static final int JWT_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000; // day * hour * minute * second * milliSecond

    // 토큰 생성
    public static String createToken(Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date expDate = new Date(new Date().getTime() + JWT_EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject((String) authentication.getPrincipal()) // 사용자
                .claim(AUTHORITIES_KEY ,authorities) // 인가
                .setIssuedAt(new Date()) // 현재 시간 기반 생성
                .setExpiration(expDate) // 만료 시간
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET) // 사용할 알고리즘 , signature
                .compact();
    }

    // jwt 토큰 아이디 추출
    public static String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    // jwt 토큰 유효성 검사
    public static boolean validationToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT Signature");
        } catch (MalformedJwtException e) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            log.error("JWT Claims String is empty");
        }
        return false;
    }

    // jwt 인가
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

}
