package eu.getsoftware.hotelico.amqp.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer
{
    private final AmqpTemplate amqpTemplate;
    
    /**
     * but which topic??
     * Queue is only for listener, and will be calculated from routingKey
     * @param payload
     * @param exchange
     * @param routingKey
     */
    public void publish(String exchange, String routingKey, Object payload) {
        log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
    }

}
