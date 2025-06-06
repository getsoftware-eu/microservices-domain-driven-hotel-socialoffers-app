package eu.getsoftware.hotelico.service.booking.config.adapter.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConditionalOnProperty(name = "app.kafka.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaTopicConfig {
    
    @Bean
    public NewTopic checkinCreatedTopic() {
        return TopicBuilder.name("checkin.checkin.created.event").build();
    }    
    
    @Bean
    public NewTopic checkinUpdatedTopic() {
        return TopicBuilder.name("checkin.checkin.updated.event").build();
    }    
    
    @Bean
    public NewTopic checkinDeletedTopic() {
        return TopicBuilder.name("checkin.checkin.deleted.event").build();
    }    
    
    @Bean
    public NewTopic chatSendTopic() {
        return TopicBuilder.name("chat.message.send.event").build();
    }
}
