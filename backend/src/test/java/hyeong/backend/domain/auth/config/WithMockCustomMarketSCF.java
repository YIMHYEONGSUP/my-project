package hyeong.backend.domain.auth.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;

public class WithMockCustomMarketSCF implements WithSecurityContextFactory<WithMockCustomerMarket> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomerMarket annotation) {
        final SecurityContext context = SecurityContextHolder.createEmptyContext();

        final UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                annotation.username(), "", Arrays.asList(new SimpleGrantedAuthority(annotation.grade())));
        context.setAuthentication(authenticationToken);
        return context;
    }
}
