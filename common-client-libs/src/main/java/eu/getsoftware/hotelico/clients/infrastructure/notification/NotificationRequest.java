package eu.getsoftware.hotelico.clients.infrastructure.notification;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
