package eu.getsoftware.hotelico.hotelapp.adapter.out.chat.messaging;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ChatKafkaListeners {
    
    @KafkaListener(topics = {"checkin.checkin.created.event", 
                             "checkin.checkin.updated.event"}, 
                   groupId = "chat.message.processor.dev")
     void listener(DomainMessage<?> data){
        
        System.out.println("Kafka listener : " + data.toString());
    }
}
