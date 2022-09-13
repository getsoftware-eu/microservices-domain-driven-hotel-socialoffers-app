package eu.getsoftware.hotelico.notification;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
