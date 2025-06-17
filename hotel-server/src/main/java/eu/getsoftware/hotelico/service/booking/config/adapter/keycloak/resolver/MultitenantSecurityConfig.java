package eu.getsoftware.hotelico.service.booking.config.adapter.keycloak.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

/**
 * Resource Server — это сервис, который принимает и проверяет JWT-токены, и на основе них решает, кого пускать (и с какими ролями).
 *
 * Здесь нужен JwtAuthenticationConverter, чтобы:
 *
 * правильно извлекать роли (authorities) из токена,
 *
 * маппить их, например, из realm_access.roles или resource_access.
 */

@Configuration
public class MultitenantSecurityConfig {

    @Bean
    public AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver() {
        return request -> {
            String realm = extractRealmFromPath(request.getRequestURI());
            
            //TODO insert keycloak URL
//            (OAuth2ResourceServerProperties properties) 
//            JwtDecoders.fromIssuerLocation(properties.getJwt().getUri..);
            
            String issuerUri = "http://localhost:8089/realms/" + realm;

            JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(issuerUri);
            JwtAuthenticationProvider authProvider = new JwtAuthenticationProvider(jwtDecoder);
            authProvider.setJwtAuthenticationConverter(jwtAuthenticationConverter());
            return authProvider::authenticate; // возвращаем AuthenticationManager
        };
    }

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
