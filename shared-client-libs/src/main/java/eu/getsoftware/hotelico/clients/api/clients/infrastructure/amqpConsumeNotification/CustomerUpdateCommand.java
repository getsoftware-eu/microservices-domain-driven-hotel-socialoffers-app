package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification;

public record CustomerUpdateCommand(
        Long customerId,
        Long hotelId,
        String customerName,
        String message
) {
}
