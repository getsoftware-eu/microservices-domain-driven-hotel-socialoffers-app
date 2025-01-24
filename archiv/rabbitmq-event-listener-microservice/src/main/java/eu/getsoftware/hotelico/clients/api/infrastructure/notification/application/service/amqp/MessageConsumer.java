package eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.service.amqp;

import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Official Consumer for Notifications
 * 
 * Если вам нужно работать с RabbitMQ, используйте @RabbitListener для обработки сообщений из очередей. 
 * Если вам нужно обрабатывать события внутри приложения, используйте @EventListener.
 */
@Component
@AllArgsConstructor
@Slf4j
public class MessageConsumer
{
//	private final IMessagePersistService messagePersistService;
	
	//We save only CONSUMED-json from queue! (its not a controller)
	@RabbitListener(queues = "${rabbitmq.queue.notification}")
	public void consumeNotification(NotificationRequest notificationRequest){
		log.info("Consumed {} from queue", notificationRequest);
//		messagePersistService.persistConsumedNotification(notificationRequest);	
	}
}
