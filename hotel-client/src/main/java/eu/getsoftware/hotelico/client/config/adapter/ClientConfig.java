package eu.getsoftware.hotelico.client.config.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {
    @Bean
    WebClient webClient(ClientRegistrationRepository clients,
                        OAuth2AuthorizedClientRepository authClients) {
        
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authClients);
        
        oauth.setDefaultClientRegistrationId("socialoffers");
        
        return WebClient.builder()
                .apply(oauth.oauth2Configuration())
                .build();
    }
}
