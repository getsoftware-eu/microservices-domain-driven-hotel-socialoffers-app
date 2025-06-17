package eu.getsoftware.hotelico.securitycommon.keycloakclient.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ConditionalOnProperty(prefix = "hotelico.security.keycloak", name = "enabled", havingValue = "true")
public class KeycloakSecurityConfig {


    // Высший приоритет: API -> JWT Access Token only
    @Bean
//    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
//            .securityMatcher(new AntPathRequestMatcher("/api/**")) // Только для /api/**
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/**").hasRole("user")
                    .requestMatchers(
                            "/login",                        // если у вас есть кастомная login-страница
                            "/oauth2/authorization/*",       // старт авторизации
                            "/login/oauth2/code/*"           // callback от Keycloak
                    ).permitAll()
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer(resourceServer -> resourceServer
                    .jwt(Customizer.withDefaults())
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
        

    // ========== 2️⃣ JWT decoder ==========

    
//    @Bean
//    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
//        return JwtDecoders.fromIssuerLocation(properties.getJwt().getIssuerUri());
//    }
//
//    // Конвертер для маппинга ролей из JWT (чтобы добавить префикс ROLE_)
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        authoritiesConverter.setAuthorityPrefix("ROLE_");
//        authoritiesConverter.setAuthoritiesClaimName("realm_access.roles");
//
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
//        return converter;
//    }

//  eu:  IF(НЕ все микросервисы вызывают другие микросервисы (например booking не вызывает никого))
//  eu: WebClient НЕ создаёт никакой нагрузки, если им не пользоваться
    
    // ========== 3️⃣ OAuth2 Client для client_credentials ==========
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository(OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService);
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    // 3️⃣ WebClient с подстановкой service-token. (если сервис вызывает REST of другой microservice)
    @Bean
    public WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth2Client.setDefaultClientRegistrationId("keycloak-client");

        return WebClient.builder()
                .apply(oauth2Client.oauth2Configuration())
                .build();
    }

}
