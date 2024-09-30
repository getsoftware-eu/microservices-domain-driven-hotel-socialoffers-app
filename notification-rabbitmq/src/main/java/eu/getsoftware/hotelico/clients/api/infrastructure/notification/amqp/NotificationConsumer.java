package eu.getsoftware.hotelico.clients.api.infrastructure.notification.amqp;

import eu.getsoftware.hotelico.clients.api.infrastructure.notification.NotificationService;
import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Official Consumer for Notifications
 */
@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer
{
	private final NotificationService notificationService;
	
	//We save only CONSUMED-json from queue! (its not a controller)
	@RabbitListener(queues = "${rabbitmq.queue.notification}")
	public void consumeNotification(NotificationRequest notificationRequest){
		log.info("Consumed {} from queue", notificationRequest);
		notificationService.persistConsumedNotification(notificationRequest);	
	}
}
