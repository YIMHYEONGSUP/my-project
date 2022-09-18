package hyeong.backend.domain.auth.config;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomerMarket {

    String username() default "market@naver.com";

//    String password() default "1234";

    String grade() default "MARKET";
}
