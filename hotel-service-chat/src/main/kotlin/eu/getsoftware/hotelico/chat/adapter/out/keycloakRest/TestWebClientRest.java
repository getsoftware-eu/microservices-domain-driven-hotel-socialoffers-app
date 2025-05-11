package eu.getsoftware.hotelico.chat.adapter.out.keycloakRest;

public class TestWebClientRest {
    
    private WebClient webClient;

    public TestWebClientRest(WebClient webClient) {
        this.webClient = webClient;
    }
    
    public String callBookingService() {
        return webClient
                .get()
                .uri("http://hotel-service-booking/api/reservations")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
