package eu.getsoftware.hotelico.clients.infrastructure.notification;

public record ChatMessageRequest(
        Long fromCustomerId,
        Long toCustomerId,
        boolean lastMessage,
        String customMsg
) {
}
