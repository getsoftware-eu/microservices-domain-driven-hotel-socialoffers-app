package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.application.infrastructure.service;

import eu.getsoftware.hotelico.clients.api.amqp.application.producer.RabbitMQMessageProducer;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.NotificationEvent;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.SocketNotificationCommand;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.MenuDTO;
import eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.service.amqp.MessageProducerWithPersistence;
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
	private MenuUserRepository menuUserRepository;
	private RestTemplate restTemplate;
	private MessageProducerWithPersistence messageProducerWithPersistence;

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
		sendViaCustomerModuleWithOwnConvertAndPersist(toCustomerId, toCustomerName, message);
	}
	
	/**
	 *  Method 3: eu: Bad: 
	 *  notification-service persisting every notification in own DB!
	 * 	via my 'notification'-module (that uses 'amqp'-module)
	 * 	
	 * 	// ACHTUNG: but not menuItem, for this you have to create extra common Entitý! and persist in notification-module 
	 * 	BUT WHERE is exchange = "internal.exchange" and routingKey ?? ANSWER: it is in Notification Module properties  !!!!		
	 * @param toCustomerId
	 * @param toCustomerName
	 * @param message
	 */
	private void sendViaCustomerModuleWithOwnConvertAndPersist(long toCustomerId, String toCustomerName, String message)
	{
		NotificationEvent myNotificationEvent = new NotificationEvent(toCustomerId, toCustomerName, toCustomerId, toCustomerName, message);
		messageProducerWithPersistence.sendPersistCheckinCreateEvent(myNotificationEvent);
	}
	
	/**
	 * 	Method 2: eu: BEST!  with my configured (added jacksonConverter()) @Bean producer
	 * 	via 'amqp'-module (rabbitMq queue @configuration to all importing modules+++)
	 * @param menuItemEntity
	 */
	private void sendViaPreconfiguredRabbitmqProducer(MenuItemEntity menuItemEntity)
	{
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = "internal.notification.routing-key";

		var domainMessage = domainMessageFactory.prepareDomainMessageForType("menuItem")
				.withEntity(menuItemEntity)
				.withCurrentTenant()
				.withAdditionalProperty("testProperty", "value")
				.build();
		
		rabbitMQMessageProducer.publishDomainMessage(exchange, routingKey, domainMessage);
	}
	
	/**
	 * 	Method 1: basic direct amqpTemplate
	 * @param menuItemEntity
	 */
	private void sendBasicDirectAmqpTemplate(MenuItemEntity menuItemEntity)
	{
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = "internal.notification.routing-key";

		Object domainMessage = domainMessageFactory.prepareDomainMessageForType("menuItem")
				.withEntity(menuItemEntity)
				.withCurrentTenant()
				.withAdditionalProperty("testProperty", "value")
				.build();
		
		amqpTemplate.convertAndSend(
				exchange,
				routingKey,
				domainMessage
		);
	}
	
	/**
	 * Listen for customer system update
	 * @param customerUpdateCommand
	 */
	@RabbitListener(queues = "${rabbitmq.queue.customer.update}")
	public void consumeNotification(SocketNotificationCommand customerUpdateCommand){
		log.info("Consumed {} from queue", customerUpdateCommand);
		log.info(customerUpdateCommand.message());
		
		handlePersistCustomerUpdate(customerUpdateCommand);
		
	}
	
	/**
	 * Notification-Service listener, with internal jackson-Converter to 'notificationRequest'-Obj
	 * @param notificationEvent
	 */
	@RabbitListener(queues = "${rabbitmq.queue.menu.notification}")
	public void consumeNotification(NotificationEvent notificationEvent){
		log.info("Consumed {} from queue", notificationEvent);
		log.info(notificationEvent.message());
		
		handleMenuNotification(notificationEvent);
		
	}
	
	private void handleMenuNotification(NotificationEvent notificationEvent)
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
		
		long hotelId = checkHotelResponse.getId();
		long hotelInitId = checkHotelResponse.getInitId();
	}
	
	public List<MenuDTO> getItems()
	{
		MenuDTO dto = MenuDTO.builder().id(123L).build();
		return List.of(dto);
	}
	
	
	private void handlePersistCustomerUpdate(SocketNotificationCommand customerUpdateCommand)
	{
		Optional<MenuUserEntity> updatedChatUserOptional = menuUserRepository.findById(customerUpdateCommand.customerId());
		
		MenuUserEntity entity;
		
		if(updatedChatUserOptional.isEmpty())
		{
			entity = new MenuUserEntity(customerUpdateCommand.customerId());
			entity.setFirstName(customerUpdateCommand.customerName());
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
