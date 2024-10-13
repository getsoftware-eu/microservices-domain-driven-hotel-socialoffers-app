package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification;

public record CustomerUpdateConsumeRequest(
        Long customerId,
        Long hotelId,
        String customerName,
        String message
) {
}
