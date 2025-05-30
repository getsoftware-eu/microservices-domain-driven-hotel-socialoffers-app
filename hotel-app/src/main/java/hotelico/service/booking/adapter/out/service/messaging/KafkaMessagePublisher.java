package hotelico.service.booking.adapter.out.service.messaging;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaMessagePublisher {

    private static final String CHECKIN_CREATED_EVENT_TYPE_NAME = "checkin.checkin.created.event";
    private static final String CHECKIN_UPDATED_EVENT_TYPE_NAME = "checkin.checkin.updated.event";
    private static final String CHECKIN_DELETED_EVENT_TYPE_NAME = "checkin.checkin.deleted.event";
    
    private final KafkaTemplate<String, DomainMessage> kafkaTemplate;

    public void publishMessage(DomainMessage<?> domainMessage) {

        kafkaTemplate.send(domainMessage.getMessageType(), domainMessage);
        
        log.info("Published message of type {}", domainMessage.getMessageType());
    }

     public void publishMessageToPartition(String messageKeyForPartition, DomainMessage<?> domainMessage) {

        kafkaTemplate.send(domainMessage.getMessageType(), messageKeyForPartition, domainMessage);
        
        log.info("Published key message of type {}", domainMessage.getMessageType());
    }

}
