package eu.getsoftware.hotelico.client.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * eu: В реактивном клиенте я просто asynchron выплевываю результат по мере поступления, сам его не обрабатывая.
 * я не могу застопорить thread и сделать действие с CustomerDTO.
 */

@RestController
public class ClientRestController {
    private final WebClient webClient;

    public ClientRestController(WebClient client) {
        this.webClient = client;
    }

    @GetMapping("/")
    //Метод не возвращает CustomerDTO напрямую, а обещание, что когда-то он его вернёт (или ошибку).
    //Spring WebFlux сам отправит CustomerDTO как JSON, когда Mono завершится.
    public Mono<CustomerDTO> callOneCustomer(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient auth) {
        return webClient.get()
                .uri("http://localhost:8088/api/customers/1")
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(auth)) 
                //Это нужно для того, чтобы WebClient знал, какой Access_token подставить в Authorization заголовок (например, Bearer <token>).
                //auth — это объект OAuth2AuthorizedClient, который уже содержит токен.
                .retrieve()
                .bodyToMono(CustomerDTO.class);
        //Mono: нужно реагировать на результат через .subscribe(), .map(), .flatMap() и т.п.


    }

    @GetMapping("/all")
    //Flux<T> → 0..N объектов, как List<T>, только реактивный
    public Flux<CustomerDTO> getAllCustomers(OAuth2AuthorizedClient auth) {
        return webClient.get()
                .uri("http://localhost:8088/api/customers")
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(auth))
                .retrieve()
                .bodyToFlux(CustomerDTO.class); // 👈 not Mono, but Flux
    }
}
