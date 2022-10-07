package hyeong.backend.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)

                .useDefaultResponseMessages(false)

                // swagger에서 jwt 토큰값 넣기위한 설정
                .securityContexts(Arrays.asList(securityContext()))
                // swagger에서 jwt 토큰값 넣기위한 설정
                .securitySchemes(Arrays.asList(apiKey()))

                .select()
//                .apis(RequestHandlerSelectors.basePackage("hyeong.backend"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hyeong's web")
                .description("Swagger 2.0")
                .version("1.0")
                .build();
    }

    // swagger에서 jwt 토큰값 넣기위한 설정 -> JWT를 인증 헤더로 포함하도록 ApiKey 를 정의.
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    //전역 AuthorizationScope를 사용하여 JWT SecurityContext를 구성.
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope =
                new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes =
                new AuthorizationScope[1];

        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("JWT", authorizationScopes));
    }
}
