package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.service;

import eu.getsoftware.hotelico.clients.api.amqp.producer.RabbitMQMessageProducer;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification.ChatMessageRequest;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.notification.CustomerUpdateRequest;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate.RabbitConverterFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelRabbitMQProducer
{
	@Autowired
	private RabbitMQMessageProducer rabbitMQMessageProducer;
	
	/** soll identisch sein, mit preconfigured producer from imported amqp lib on maven **/
	@Autowired
	private AmqpTemplate amqpTemplate;	
	
	@Autowired
	private AsyncRabbitTemplate asyncRabbitTemplate;
	
	/**
	 * expected the user is the one authenticated with the WebSocket session
	 */
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	public String produceSimpWebsocketMessage(String destination, CustomerNotificationDTO dto) {
		
		simpMessagingTemplate.convertAndSend(destination, dto);
		return "Message(" + dto + ")" + " has been produced.";
	}
	public String produceSimpWebsocketMessage(String destination, List<? extends Object> list) {
		
		simpMessagingTemplate.convertAndSend(destination, list);
		return "Message(" + list + ")" + " has been produced.";
	}
	
	public String produceSimpWebsocketMessage(String destination, ChatMsgDTO dto) {
		
		simpMessagingTemplate.convertAndSend(destination, dto);
		return "Message(" + dto + ")" + " has been produced.";
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
	 * asynchr direct exchange, but without own correlation ID,
	 * no other ec2 instance can get this response and operate it scalable!
	 * @param chatMessageRequest
	 * @return
	 */
	public RabbitConverterFuture<ChatMsgDTO> sendAsynchDirectExchangeMethodCall(ChatMessageRequest chatMessageRequest)
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
	 * 	Method 2: with my configured (added jacksonConverter()) @Bean producer
	 * 	via 'amqp'-module (configured)
	 * @param customerUpdateRequest
	 */
	public void sendTopicViaPreconfiguredRabbitmqProducer(CustomerUpdateRequest customerUpdateRequest)
	{
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = "internal.notification.customer-update";
		
		rabbitMQMessageProducer.publish(exchange, routingKey, customerUpdateRequest);
	}
	
	public void sendChatMessageRequest(ChatMessageRequest chatMessageRequest)
	{
		//only for 1 and 2 Method we have to write this system variables: 
		String exchange = "internal.exchange";
		String routingKey = "internal.chat.message-request";
		
		rabbitMQMessageProducer.publish(exchange, routingKey, chatMessageRequest);
	}
	
	public void registerPush(CustomerDTO dto)
	{
		//        ortcClient.subscribeWithNotifications("myChannel", true, new OnMessage() {
		//            public void run(OrtcClient sender, String channel, String message) {
		//                Log.i(TAG, String.format("Message or push notification on channel %s: %s ",
		//                        channel, message));
		//            };
		//        });
		
		CustomerUpdateRequest customerUpdateRequest = new CustomerUpdateRequest(
				dto.getId(),
				dto.getFirstName(),
				dto.getEmail());
		
		sendTopicViaPreconfiguredRabbitmqProducer(customerUpdateRequest);
	}
}
