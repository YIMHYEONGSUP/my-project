package hyeong.backend.global.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**")
                .antMatchers("/resources/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/h2-console") // h2로 데이터베이스 테스트
                .antMatchers("/images/**")
                .antMatchers("/swagger-ui/**")

                // swagger
                .antMatchers( "/v3/api-docs", "/configuration/ui", "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html", "/webjars/**","/swagger/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/v1/join").permitAll()
                .anyRequest().authenticated();

       /* http
                .sessionManagement()
                .maximumSessions(3) // 세션 최대 허용 수
                .maxSessionsPreventsLogin(false); //중복 로그인시 이전 계정 로그아웃*/
    }
}
