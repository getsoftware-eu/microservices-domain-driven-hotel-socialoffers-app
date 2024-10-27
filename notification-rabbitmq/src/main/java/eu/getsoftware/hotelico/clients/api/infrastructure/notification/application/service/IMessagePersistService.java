package eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.service;

import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;

public interface IMessagePersistService
{
    void persistConsumedNotification(NotificationRequest notificationRequest);
}

