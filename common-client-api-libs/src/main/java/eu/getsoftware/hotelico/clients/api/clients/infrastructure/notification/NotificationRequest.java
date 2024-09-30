package eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification;

public record NotificationRequest(
        Long toCustomerId,
        String toCustomerName,
        String message
) {
}
