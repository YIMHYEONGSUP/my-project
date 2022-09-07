package hyeong.backend.global.config;


import hyeong.backend.domain.auth.details.MemberDetailServiceCustom;
import hyeong.backend.global.common.TokenProvider;
import hyeong.backend.global.jwt.JwtAccessDeniedHandler;
import hyeong.backend.global.jwt.JwtAuthenticationEntryPoint;
import hyeong.backend.global.jwt.JwtSecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CorsFilter corsFilter;
    private final MemberDetailServiceCustom memberDetailServiceCustom;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailServiceCustom);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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

                .addFilterBefore(corsFilter , UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST,"/api/v1/members/join").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/v1/members/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/v1/members/reissue").permitAll()
                    .antMatchers("/swagger-resources/**").permitAll()
                .anyRequest().authenticated();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .apply(new JwtSecurityConfig(tokenProvider));

        http.formLogin().disable();


    }
}
