package eu.getsoftware.hotelico.notification.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import eu.getsoftware.hotelico.notification.NotificationRequest;
import eu.getsoftware.hotelico.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer
{
	private final NotificationService notificationService;
	
	@RabbitListener(queues = "${rabbitmq.queue.notification}")
	public void consumeNotification(NotificationRequest notificationRequest){
		log.info("Consumed {} from queue", notificationRequest);
		notificationService.send(notificationRequest);	}
}
