package eu.getsoftware.hotelico.clients.infrastructure.notification;

public record NotificationRequest(
        Long toCustomerId,
        String toCustomerName,
        String message
) {
}
