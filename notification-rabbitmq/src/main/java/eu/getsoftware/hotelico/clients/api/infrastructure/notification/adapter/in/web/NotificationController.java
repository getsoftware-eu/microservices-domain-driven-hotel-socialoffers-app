package eu.getsoftware.hotelico.clients.api.infrastructure.notification.adapter.in.web;

import eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.service.IMessagePersistService;
import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;
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

    private final IMessagePersistService messagePersistService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("New notification... {}", notificationRequest);
        messagePersistService.persistConsumedNotification(notificationRequest);
    }
}
