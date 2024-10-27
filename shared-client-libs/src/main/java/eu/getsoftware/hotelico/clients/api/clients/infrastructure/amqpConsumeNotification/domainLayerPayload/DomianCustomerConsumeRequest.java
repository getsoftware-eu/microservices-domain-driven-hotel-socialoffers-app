package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload;

public record DomianCustomerConsumeRequest(
        Long customerId,
        Long hotelId,
        String customerName,
        String message
) {
}
