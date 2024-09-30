package eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification;

public record CustomerUpdateRequest(
        Long customerId,
        String customerName,
        String message
) {
}
