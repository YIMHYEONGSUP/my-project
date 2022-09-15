package hyeong.backend.global.configs;

import hyeong.backend.global.interceptors.BasicInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 인터셉터 사용 예제
        registry.addInterceptor(new BasicInterceptor())
                .excludePathPatterns("/**");
    }


}
