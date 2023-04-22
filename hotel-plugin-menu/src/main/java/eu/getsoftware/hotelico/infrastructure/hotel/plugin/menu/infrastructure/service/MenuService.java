package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.infrastructure.service;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import eu.getsoftware.hotelico.amqp.RabbitMQMessageProducer;
import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.infrastructure.notification.CustomerUpdateRequest;
import eu.getsoftware.hotelico.clients.infrastructure.notification.NotificationRequest;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.domain.model.MenuItem;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.domain.model.MenuUserEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.infrastructure.repository.MenuUserRepository;
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
	
	@Autowired
	private MenuUserRepository menuUserRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Queue will be calculated from routing-key in exchange
	 */
	public void initSendNotification()
	{
		MenuItem menuItem = new MenuItem();
		menuItem.setAmount(2);
		
		// Method 1: basic direct amqpTemplate
		sendBasicDirectAmqpTemplate(menuItem);
		
		// Method 2: with my configured (added jacksonConverter()) @Bean producer
		// via 'amqp'-module (configured)
		sendViaPreconfiguredRabbitmqProducer(menuItem);
		
		long toCustomerId = 1;
		String toCustomerName = "Bob";
		String message = "Hi there";
		
		// Method 3: notification-service persisting every notification in own DB!
		// via my 'notification'-module (that uses 'amqp'-module)
		// ACHTUNG: but not menuItem, for this you have to create extra common Entitý! and persist in notification-module
		sendViaCustomerModuleWithConvertAndPersist(toCustomerId, toCustomerName, message);
	}
	
	/**
	 *  Method 3: notification-service persisting every notification in own DB!
	 * 	via my 'notification'-module (that uses 'amqp'-module)
	 * 	
	 * 	// ACHTUNG: but not menuItem, for this you have to create extra common Entitý! and persist in notification-module 
	 * 	BUT WHERE is exchange = "internal.exchange" and routingKey ?? ANSWER: it is in Notification Module properties  !!!!		
	 * @param toCustomerId
	 * @param toCustomerName
	 * @param message
	 */
	private void sendViaCustomerModuleWithConvertAndPersist(long toCustomerId, String toCustomerName, String message)
	{
		NotificationRequest myNotification = new NotificationRequest(toCustomerId, toCustomerName, message);
		notificationService.send(myNotification);
	}
	
	/**
	 * 	Method 2: with my configured (added jacksonConverter()) @Bean producer
	 * 	via 'amqp'-module (configured)
	 * @param menuItem
	 */
	private void sendViaPreconfiguredRabbitmqProducer(MenuItem menuItem)
	{
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = "internal.notification.routing-key";
		
		rabbitMQMessageProducer.publish(menuItem, exchange, routingKey);
	}
	
	/**
	 * 	Method 1: basic direct amqpTemplate
	 * @param menuItem
	 */
	private void sendBasicDirectAmqpTemplate(MenuItem menuItem)
	{
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = "internal.notification.routing-key";
		
		amqpTemplate.convertAndSend(
				exchange,
				routingKey,
				menuItem
		);
	}
	
	/**
	 * Listen for customer system update
	 * @param customerUpdateRequest
	 */
	@RabbitListener(queues = "${rabbitmq.queue.customer.update}")
	public void consumeNotification(CustomerUpdateRequest customerUpdateRequest){
		log.info("Consumed {} from queue", customerUpdateRequest);
		log.info(customerUpdateRequest.message());
		
		handleSystemCustomerUpdate(customerUpdateRequest);
		
	}
	
	/**
	 * Notification-Service listener, with internal jackson-Converter to 'notificationRequest'-Obj
	 * @param notificationRequest
	 */
	@RabbitListener(queues = "${rabbitmq.queue.menu.notification}")
	public void consumeNotification(NotificationRequest notificationRequest){
		log.info("Consumed {} from queue", notificationRequest);
		log.info(notificationRequest.message());
		
		handleMenuNotification(notificationRequest);
		
	}
	
	private void handleMenuNotification(NotificationRequest notificationRequest)
	{
		throw new UnsupportedOperationException("not implemented");
	}
	
	/**
	 * Basic rabbitMq Listener
	 * @param message
	 */
	@RabbitListener(queues = "${rabbitmq.queue.menu.notification1}")
	public void receiveMessageFromQueue1(String message) {
		log.info("Received  message: {} ", message);
	}
	
	public void restRequestAnotherModule(long customerId){
		HotelDTO checkHotelResponse = restTemplate.getForObject(
				"http://HOTEL-APP/api/v1/hotels/customer/{customerId}/hotel",
				HotelDTO.class,
				customerId
		);
		
		Long hotelId = checkHotelResponse.getId();
		Long hotelInitId = checkHotelResponse.getInitId();
	}
	
	public List<MenuItem> getItems()
	{
		return List.of(new MenuItem());
	}
	
	
	private void handleSystemCustomerUpdate(CustomerUpdateRequest customerUpdateRequest)
	{
		Optional<MenuUserEntity> updatedChatUserOptional = menuUserRepository.findById(customerUpdateRequest.customerId());
		
		MenuUserEntity entity;
		
		if(updatedChatUserOptional.isEmpty())
		{
			entity = new MenuUserEntity(customerUpdateRequest.customerId());
			entity.setFirstName(customerUpdateRequest.customerName());
		}
		else {
			MenuUserEntity updatedChatUser = updatedChatUserOptional.get();
			entity = menuUserRepository.findByUserId(updatedChatUser.getId());
			
			entity.setEmail(updatedChatUser.getEmail());
			entity.setFirstName(updatedChatUser.getFirstName());
		}
		
		MenuUserEntity persistedEntity = menuUserRepository.save(entity);
	}
}
