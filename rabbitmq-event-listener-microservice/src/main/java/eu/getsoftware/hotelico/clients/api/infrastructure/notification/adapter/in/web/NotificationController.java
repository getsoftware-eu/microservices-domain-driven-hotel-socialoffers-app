package eu.getsoftware.hotelico.clients.api.infrastructure.notification.adapter.in.web;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.eventConsumeNotification.NotificationEvent;
import eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.service.amqp.MessageProducerWithPersistence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
@Slf4j
public class NotificationController
{
    private MessageProducerWithPersistence messageProducerWithPersistence;

    @PostMapping
    public void sendNotification(@RequestBody NotificationEvent notificationEvent) {
        log.info("New event... {}", notificationEvent);
        messageProducerWithPersistence.sendPersistCheckinCreateEvent(notificationEvent);
    }
}
