package eu.getsoftware.hotelico.chat.config.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.protocol.Message
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConsumerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String? = null

    fun consumerConfig(): Map<String, Any?> {
        val props: MutableMap<String, Any?> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        //        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Message> {
        val messageJsonDeserializer = JsonDeserializer<Message>()
        messageJsonDeserializer.addTrustedPackages("eu.getsoftware")

        return DefaultKafkaConsumerFactory(
            consumerConfig(),
            StringDeserializer(),
            messageJsonDeserializer
        )
    }

    @Bean
    fun listenerFactory(
        consumerFactory: ConsumerFactory<String, Message>
    ): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Message>> {
        val listenerFactory = ConcurrentKafkaListenerContainerFactory<String, Message>()

        listenerFactory.consumerFactory = consumerFactory
        return listenerFactory
    }
}
