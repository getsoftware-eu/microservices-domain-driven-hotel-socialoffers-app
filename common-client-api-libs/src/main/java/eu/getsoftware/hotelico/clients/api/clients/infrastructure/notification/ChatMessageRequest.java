package eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification;

public record ChatMessageRequest(
        Long fromCustomerId,
        Long toCustomerId,
        boolean lastMessage,
        String customMsg
) {
}
