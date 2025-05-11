package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.portserviceimpl;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessage;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaCheckinConsumerService {
    private Consumer<String, DomainMessage<?>> consumer;

    public KafkaCheckinConsumerService() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "checkin-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "your.event.deserializer"); // десериализатор события

        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList("checkin.checkin.created.event"));
    }

    public void listenToEvents() {
        while (true) {
            ConsumerRecords<String, DomainMessage<?>> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, DomainMessage<?>> record : records) {
                DomainMessage<?> eventMEssage = record.value();

                // Логика создания счета на основе checkinId
                System.out.println("Creating invoice for Checkin ID: " + eventMEssage);
            }
        }
    }
}
