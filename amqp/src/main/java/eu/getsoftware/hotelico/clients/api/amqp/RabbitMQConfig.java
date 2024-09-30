package eu.getsoftware.hotelico.clients.api.amqp;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfig
{
	//eugen 2022: send and receive msgs
	private final ConnectionFactory connectionFactory;
	
	//Eugen 2022: send to channel
	
	/**
	 * use RabbitTemplate as a AmqpTemplate! - valid for all importing maven spring-contexts
	 * @return
	 */
	@Bean
	public AmqpTemplate amqpTemplate(){
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jacksonConverter());
		return rabbitTemplate;
	}
	
	//eugen 2022: receive from queue
	@Bean
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(jacksonConverter());
		return factory;
	}
	
	@Bean
	public MessageConverter jacksonConverter(){
//		Jackson2ObjectMapperBuilder.json().modules()...
		MessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
		return jackson2JsonMessageConverter;
	}
	
}
