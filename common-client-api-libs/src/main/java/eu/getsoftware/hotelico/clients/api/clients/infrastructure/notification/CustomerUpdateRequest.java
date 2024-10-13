package eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification;

public record CustomerUpdateRequest(
        Long customerId,
        Long hotelId,
        String customerName,
        String message
) {
}
