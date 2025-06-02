package eu.getsoftware.hotelico.chat.config.adapter.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.protocol.Message
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String? = null

    fun producerConfig(): Map<String, Any?> {
        val props: MutableMap<String, Any?> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] =
            StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] =
            JsonSerializer::class.java
        return props
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, Message> {
        return DefaultKafkaProducerFactory(producerConfig())
    }

    /**
     * eu: create KafkaTemplate on basis of ProducerFactory
     * @param producerFactory
     * @return
     */
    @Bean
    fun kafkaTemplate(
        producerFactory: ProducerFactory<String, Message>
    ): KafkaTemplate<String, Message> {
        return KafkaTemplate(producerFactory)
    }
}
