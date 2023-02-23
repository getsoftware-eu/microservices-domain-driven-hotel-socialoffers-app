package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import eu.getsoftware.hotelico.amqp.RabbitMQMessageProducer;
import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.domain.MenuItem;
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
		
		String exchange = "internal.exchange";
		String routingKey = "internal.notification.routing-key";
		
		// Method 1: basic amqpTemplate
		amqpTemplate.convertAndSend(
				exchange,
				routingKey,
				menuItem
		);
		
		// Method 2: with my configured (added jacksonConverter()) @Bean producer
		rabbitMQMessageProducer.publish(menuItem, exchange, routingKey);
		
		// Method 3: via my notification module, persisting every notification in module-DB
		long toCustomerId = 1;
		String toCustomerName = "Bob";
		String message = "Hi there";
		
		NotificationRequest myNotification = new NotificationRequest(toCustomerId, toCustomerName, message);
		notificationService.send(myNotification);
	};
	
	@RabbitListener(queues = "${rabbitmq.queue.menu.notification}")
	public void consumeNotification(NotificationRequest notificationRequest){
		log.info("Consumed {} from queue", notificationRequest);
		log.info(notificationRequest.message());	
	}
}
