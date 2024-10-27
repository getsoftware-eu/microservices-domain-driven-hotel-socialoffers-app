package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.outPortServiceImpl.microservice;

import eu.getsoftware.hotelico.clients.api.amqp.application.producer.RabbitMQMessageProducer;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.ChatMessageConsumeRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.CustomerUpdateConsumeRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IMessagingProducerService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.RabbitConverterFuture;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

/**
 * eu: Producer of asynchron messaging
 */
@Service
@AllArgsConstructor
public class MessagingRabbitMQProducer implements IMessagingProducerService<HotelEvent>
{
	/** soll identisch sein, mit preconfigured producer from imported amqp lib on maven **/
	private final AmqpTemplate amqpTemplate;
	private final RabbitMQMessageProducer rabbitMQMessageProducer;
	private final AsyncRabbitTemplate asyncRabbitTemplate;

	@Override
	public void sendCustomerNotification(CustomerUpdateCommand requestDTO, HotelEvent hotelEvent) {
		
		String exchange = "internal.exchange";
		String routingKey = getRabbitMQTopicFromEventEnum(hotelEvent);
		
		rabbitMQMessageProducer.publish(exchange, routingKey, requestDTO);
	}

	/**
	 * 	Chat with Method 2: with my configured (added jacksonConverter()) @Bean producer
	 * 	via 'amqp'-module (configured)
	 * @param chatMessageRequest
	 */
	public void sendChatMessageTopicRequest(ChatMessageConsumeRequest chatMessageRequest)
	{
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = "internal.chat.message-request";

		rabbitMQMessageProducer.publish(exchange, routingKey, chatMessageRequest);
	}

	public String getRabbitMQTopicFromEventEnum(HotelEvent hotelEvent) {

		String routingKey;

		switch (hotelEvent)
		{
			case HotelEvent.EVENT_CHAT_SEND_MESSAGE -> {
				routingKey = "internal.chat.message-request"; break;
			}
			case HotelEvent.EVENT_CHECKIN -> {
				routingKey = "internal.notification.customer-update"; break;
			}
			default -> throw new RuntimeException("invalid hotelEvent"); //break;
		};

		return routingKey;

	}
	
	public String produceAMQPMessage(String topicExchangeName, String routingKey, String message) {
		amqpTemplate.convertAndSend(topicExchangeName, routingKey, message);
		return "Message(" + message + ")" + " has been produced.";
	}
	
//	public String produceRabbitMessage(String topicExchangeName, String routingKey, String message) {
//		rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
//		return "Message(" + message + ")" + " has been produced.";
//	}
	
	/**
	 * asynchr direct exchange = WITHOUT (messaging)-TOPIC (topic exchange) 
	 * eu: it's like 1:1 communication, without broadcast via topic
	 * but without own correlation ID,
	 * no other ec2 instance can get this response and operate it scalable!
	 * @param chatMessageRequest
	 * @return
	 */
	public RabbitConverterFuture<ChatMsgDTO> sendAsynchDirectExchangeMethodCall(ChatMessageConsumeRequest chatMessageRequest)
	{
		String directExchange = "internal.exchange";
		/**
		 * queue(s) with the binding key that exactly matches the routing key of the message
		 */
		String ROUTING_KEY = "rabbitmq.queue.chat.request";
				
		RabbitConverterFuture<ChatMsgDTO> rabbitConverterFuture =
				asyncRabbitTemplate.convertSendAndReceiveAsType(
						directExchange,
						ROUTING_KEY,
						chatMessageRequest,
						new ParameterizedTypeReference<>()
						{
						});
		
		return rabbitConverterFuture;
	}
	
	
	/**
	 * 	Method 2: asynchron with TOPIC(exchange)! Because we use "Routing Key" broadcast
	 * 	with my configured (added jacksonConverter()) @Bean producer
	 * 	via 'amqp'-module (configured)
	 * @param customerUpdateRequest
	 */
	public void sendTopicViaPreconfiguredRabbitmqProducer(CustomerUpdateConsumeRequest customerUpdateRequest, HotelEvent hotelEvent)
	{
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = getRabbitMQTopicFromEventEnum(hotelEvent);
		
		rabbitMQMessageProducer.publish(exchange, routingKey, customerUpdateRequest);
	}

	public void sendCustomerNotification2(CustomerUpdateConsumeRequest requestDTO, HotelEvent hotelEvent)
	{
		//        ortcClient.subscribeWithNotifications("myChannel", true, new OnMessage() {
		//            public void run(OrtcClient sender, String channel, String message) {
		//                Log.i(TAG, String.format("Message or push notification on channel %s: %s ",
		//                        channel, message));
		//            };
		//        });


		sendTopicViaPreconfiguredRabbitmqProducer(requestDTO, hotelEvent);
	}

	public void notificateAboutEntityEvent(CustomerDTO dto, HotelEvent hotelEvent, String eventContent, long entityId) {

		String exchange = "internal.exchange";
		String routingKey = getRabbitMQTopicFromEventEnum(hotelEvent);

		Object payloadFromEvent = extractPayloadFromHotelEvent(hotelEvent, eventContent, entityId);

		rabbitMQMessageProducer.publish(exchange, routingKey, payloadFromEvent);

		
	}

	private Object extractPayloadFromHotelEvent(HotelEvent hotelEvent, String eventContent, long entityId) {
	}


}
