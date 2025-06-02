package eu.getsoftware.hotelico.service.booking.config.adapter.resolver;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
 
@Configuration
public class MultitenantSecurityConfig {

//    @Bean
//    // eu: original:   public AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver() {
//    public JwtAuthenticationProvider authenticationManagerResolver() {
//        return request -> {
//            String realm = extractRealmFromPath(request.getRequestURI());
//            String issuerUri = "http://localhost:8080/realms/" + realm;
//
//            JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(issuerUri);
//            JwtAuthenticationProvider authProvider = new JwtAuthenticationProvider(jwtDecoder);
//            authProvider.setJwtAuthenticationConverter(jwtAuthenticationConverter());
//            return authProvider;
//        };
//    }

    private String extractRealmFromPath(String uri) {
        // Пример: /api/{realm}/...
        String[] parts = uri.split("/");
        if (parts.length > 2) {
            return parts[2];
        }
        return "default";
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles"); // или "realm_access.roles"
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }
}
