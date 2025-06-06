package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.portserviceimpl;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.CheckinDomainEvent;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessage;
import eu.getsoftware.hotelico.clients.common.domain.ids.CheckinDomainEntityId;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessagePayloadStatus.QUEUED;

public class KafkaWithoutBootCheckinProducerService {
    private Producer<String, DomainMessage<?>> producer;

    public KafkaWithoutBootCheckinProducerService() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "your.event.serializer"); // сериализатор события

        this.producer = new KafkaProducer<>(props);
    }

    public void createCheckin(CheckinDomainEntityId checkinEntityId) {
        // Логика создания заказа (например, сохранение в базе данных)

        CheckinUpdatedEventPayload eventPayload = CheckinUpdatedEventPayload.builder()
                .entityId(checkinEntityId)
                .status(QUEUED)
                .build();

        DomainMessage<?> eventMessage = CheckinDomainEvent.builder()
                .messageType("checkin.checkin.created.event")
                .tenantId(1L)
                .payload(eventPayload)
                .build();
        
        // Публикация события
//        CheckinCreatedEvent event = new CheckinCreatedEvent(checkinEntityId);
        String KEY_FOR_KAFKA_MESSAGE_SAME_PARTITION = checkinEntityId.uuidValue();
        
        producer.send(new ProducerRecord<>(eventMessage.getMessageType(), KEY_FOR_KAFKA_MESSAGE_SAME_PARTITION, eventMessage));
    }
}
