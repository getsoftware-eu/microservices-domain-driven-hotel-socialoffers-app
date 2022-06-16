package eu.getsoftware.hotelico.notification;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig
{
	@Value("${rabbitmq.exchanges.internal}")
	private String internalExchangeName;	
	
	@Value("${rabbitmq.queue.notification}")
	private String notificationQueueName;	
	
	@Value("${rabbitmq.routing-keys.internal-notification}")
	private String internalNotificationRoutingKeyName;
	
	@Bean
	public TopicExchange internalTopicExchange(){
		return new TopicExchange(this.internalExchangeName);
	}
	
	@Bean
	public Queue notificationQueueName(){
		return new Queue(this.notificationQueueName);
	}
	
	// bind 'Queue' to 'Topic-Exchange' using 'Routing-Key'(binding)
	@Bean
	public Binding interalToNotificationBinding(){
		return BindingBuilder
				.bind(notificationQueueName())
				.to(internalTopicExchange())	
				.with(this.internalNotificationRoutingKeyName); //with binding
	}
	
	public String getInternalExchangeName()
	{
		return internalExchangeName;
	}
	
	public String getInternalNotificationRoutingKeyName()
	{
		return internalNotificationRoutingKeyName;
	}
	
	public String getNotificationQueueName()
	{
		return notificationQueueName;
	}
}
