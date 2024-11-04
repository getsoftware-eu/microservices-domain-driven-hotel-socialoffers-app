package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.portServiceImpl;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinEntityId;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
public class KafkaCheckinProducerService {
    private Producer<String, CheckinCreatedEvent> producer;

    public OrderService() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "your.event.serializer"); // сериализатор события

        this.producer = new KafkaProducer<>(props);
    }

    public void createOrder(CheckinEntityId checkinEntityId) {
        // Логика создания заказа (например, сохранение в базе данных)

        // Публикация события
        CheckinCreatedEvent event = new CheckinCreatedEvent(checkinEntityId);
        producer.send(new ProducerRecord<>("checkin-created-topic", event.getCheckinId(), event));
    }
}
