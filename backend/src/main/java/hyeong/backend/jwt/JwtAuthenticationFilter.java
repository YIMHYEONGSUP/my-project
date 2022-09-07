package hyeong.backend.jwt;

import hyeong.backend.jwt.JwtTokenProvider;
import hyeong.backend.jwt.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 토큰 꺼내기
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && JwtTokenProvider.validationToken(jwt)) {
                // id 꺼내기
                String userId = JwtTokenProvider.getUserIdFromJWT(jwt);
                // id 인증
                UserAuthentication authentication = new UserAuthentication(userId, null, null);
                // 기본 details 세팅
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 세션 사용을 위한 SecurityContext 에 Authentication 등록
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                if (StringUtils.isEmpty(jwt)) {
                    request.setAttribute("unauthorization", "401 Error has Not Authorization Key");
                }

                if (JwtTokenProvider.validationToken(jwt)) {
                    request.setAttribute("unauthorization", "401-001 Expired Authorization Key");
                }
            }
        } catch (Exception e) { // Exception 보다 좋은 방법 공부
            logger.error("Could Not Set User Authentication In Security Context" , e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")
                ? bearerToken.substring("Bearer ".length()) : null;
    }
}
