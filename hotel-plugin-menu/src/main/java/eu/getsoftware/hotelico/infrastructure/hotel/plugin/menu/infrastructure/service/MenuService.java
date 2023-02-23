package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.infrastructure.service;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import eu.getsoftware.hotelico.amqp.RabbitMQMessageProducer;
import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.domain.model.MenuItem;
import eu.getsoftware.hotelico.infrastructure.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MenuService
{
	@Autowired
	private AmqpTemplate amqpTemplate;	
	
	@Autowired
	private RabbitMQMessageProducer rabbitMQMessageProducer;	
	
	@Autowired
	private NotificationService notificationService;
	
	/**
	 * Queue will be calculated from routing-key in exchange
	 */
	public void initSendNotification()
	{
		MenuItem menuItem = new MenuItem();
		menuItem.setAmount(2);
		
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = "internal.notification.routing-key";
		
		// Method 1: basic direct amqpTemplate
		amqpTemplate.convertAndSend(
				exchange,
				routingKey,
				menuItem
		);
		
		// Method 2: with my configured (added jacksonConverter()) @Bean producer
		// via 'amqp'-module (configured)
		rabbitMQMessageProducer.publish(menuItem, exchange, routingKey);
		
		// Method 3: notification-service persisting every notification in own DB!
		// via my 'notification'-module (that uses 'amqp'-module)
		long toCustomerId = 1;
		String toCustomerName = "Bob";
		String message = "Hi there";
		
		NotificationRequest myNotification = new NotificationRequest(toCustomerId, toCustomerName, message);
		notificationService.send(myNotification);
	};
	
	/**
	 * Notification-Service listener, with internal jackson-Converter to 'notificationRequest'-Obj
	 * @param notificationRequest
	 */
	@RabbitListener(queues = "${rabbitmq.queue.menu.notification}")
	public void consumeNotification(NotificationRequest notificationRequest){
		log.info("Consumed {} from queue", notificationRequest);
		log.info(notificationRequest.message());	
	}
	
	/**
	 * Basic rabbitMq Listener
	 * @param message
	 */
	@RabbitListener(queues = "${rabbitmq.queue.menu.notification1}")
	public void receiveMessageFromQueue1(String message) {
		log.info("Received  message: {} ", message);
	}
	
	public List<MenuItem> getItems()
	{
		return List.of(new MenuItem());
	}
}
