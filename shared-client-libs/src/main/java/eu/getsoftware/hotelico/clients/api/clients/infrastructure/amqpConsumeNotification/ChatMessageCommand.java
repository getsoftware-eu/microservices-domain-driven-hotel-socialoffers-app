package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification;

public record ChatMessageConsumeRequest(
        Long fromCustomerId,
        Long toCustomerId,
        boolean lastMessage,
        String customMsg
) {
}
