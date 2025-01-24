package eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.service.amqp;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.eventConsumeNotification.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageProducerWithPersistence {
    
    private final RabbitTemplate rabbitTemplate;

    public void sendPersistCheckinCreateEvent(NotificationEvent event) {
        rabbitTemplate.convertAndSend("checkin.checkin.created.event", event);
    }
}
