package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.portServiceImpl;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaCheckinConsumerService {
    private Consumer<String, CheckinCreatedEvent> consumer;

    public KafkaCheckinConsumerService() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "billing-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "your.event.deserializer"); // десериализатор события

        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList("checkin-created-topic"));
    }

    public void listenToEvents() {
        while (true) {
            ConsumerRecords<String, CheckinCreatedEvent> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, CheckinCreatedEvent> record : records) {
                CheckinCreatedEvent event = record.value();
                String checkinId = event.getCheckinId();

                // Логика создания счета на основе checkinId
                System.out.println("Creating invoice for Checkin ID: " + checkinId);
            }
        }
    }
}
