package chat.config.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicConfig {
    
    @Bean
    fun checkinCreatedTopic(): NewTopic {
        return TopicBuilder.name("checkin.checkin.created.event").build()
    }

    @Bean
    fun checkinUpdatedTopic(): NewTopic {
        return TopicBuilder.name("checkin.checkin.updated.event").build()
    }

    @Bean
    fun checkinDeletedTopic(): NewTopic {
        return TopicBuilder.name("checkin.checkin.deleted.event").build()
    }

    @Bean
    fun chatSendTopic(): NewTopic {
        return TopicBuilder.name("chat.message.send.event").build()
    }
}
