package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification;

public record SocketNotificationCommand(
        Long customerId,
        Long hotelId,
        String customerName,
        String message
) {
}
