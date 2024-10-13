package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.application.infrastructure.service;

import eu.getsoftware.hotelico.clients.api.amqp.producer.RabbitMQMessageProducer;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.CustomerUpdateConsumeRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.NotificationConsumeRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.MenuDTO;
import eu.getsoftware.hotelico.clients.api.infrastructure.notification.NotificationService;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model.MenuItemEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.model.MenuUserEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.out.persistence.repository.MenuUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class AsyncMSCommunicationService
{
	private AmqpTemplate amqpTemplate;	
	private RabbitMQMessageProducer rabbitMQMessageProducer;	
	private NotificationService notificationService;	
	private MenuUserRepository menuUserRepository;
	private RestTemplate restTemplate;
	
	/**
	 * Queue will be calculated from routing-key in exchange
	 */
	public void initSendNotification()
	{
		MenuItemEntity menuItem = new MenuItemEntity();
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
		NotificationConsumeRequest myNotification = new NotificationConsumeRequest(toCustomerId, toCustomerName, toCustomerId, toCustomerName, message);
		notificationService.persistConsumedNotification(myNotification);
	}
	
	/**
	 * 	Method 2: with my configured (added jacksonConverter()) @Bean producer
	 * 	via 'amqp'-module (configured)
	 * @param menuItem
	 */
	private void sendViaPreconfiguredRabbitmqProducer(MenuItemEntity menuItem)
	{
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = "internal.notification.routing-key";
		
		rabbitMQMessageProducer.publish(exchange, routingKey, menuItem);
	}
	
	/**
	 * 	Method 1: basic direct amqpTemplate
	 * @param menuItem
	 */
	private void sendBasicDirectAmqpTemplate(MenuItemEntity menuItem)
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
	public void consumeNotification(CustomerUpdateConsumeRequest customerUpdateRequest){
		log.info("Consumed {} from queue", customerUpdateRequest);
		log.info(customerUpdateRequest.message());
		
		handleSystemCustomerUpdate(customerUpdateRequest);
		
	}
	
	/**
	 * Notification-Service listener, with internal jackson-Converter to 'notificationRequest'-Obj
	 * @param notificationRequest
	 */
	@RabbitListener(queues = "${rabbitmq.queue.menu.notification}")
	public void consumeNotification(NotificationConsumeRequest notificationRequest){
		log.info("Consumed {} from queue", notificationRequest);
		log.info(notificationRequest.message());
		
		handleMenuNotification(notificationRequest);
		
	}
	
	private void handleMenuNotification(NotificationConsumeRequest notificationRequest)
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
	
	public List<MenuDTO> getItems()
	{
		MenuDTO dto = MenuDTO.builder().id(123L).build();
		return List.of(dto);
	}
	
	
	private void handleSystemCustomerUpdate(CustomerUpdateConsumeRequest customerUpdateRequest)
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
