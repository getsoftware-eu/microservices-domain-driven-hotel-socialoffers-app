package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload;

public record DomainCustomerCommand(
        Long customerId,
        Long hotelId,
        String customerName,
        String message
) {
}
