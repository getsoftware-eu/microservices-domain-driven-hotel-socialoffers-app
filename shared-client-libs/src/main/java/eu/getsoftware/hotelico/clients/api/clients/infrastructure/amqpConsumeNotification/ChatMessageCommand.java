package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification;

public record ChatMessageCommand(
        Long fromCustomerId,
        Long toCustomerId,
        boolean lastMessage,
        String customMsg
) {
}
