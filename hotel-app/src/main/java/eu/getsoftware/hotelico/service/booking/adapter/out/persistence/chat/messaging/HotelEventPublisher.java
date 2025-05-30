package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.chat.messaging;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.HotelUpdateEventMessagePayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessage;
import eu.getsoftware.hotelico.service.booking.adapter.out.service.messaging.KafkaMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessagePayloadStatus.QUEUED;

@Component
@Slf4j
@RequiredArgsConstructor
/*public*/ class HotelEventPublisher {

    private final KafkaMessagePublisher kafkaMessagePublisher;

//    public void publishChatSentEvent(ChatMsgDTO chatMsgDTO){
//        publishMessage("chat.message.sent.event", chatMsgDTO);
//    }

    private void publishMessage(String messageType, HotelDTO hotelDTO) {

        HotelUpdateEventMessagePayload eventPayload = HotelUpdateEventMessagePayload.builder()
                .messageId(hotelDTO.getConsistencyId())
                .message(hotelDTO.getName())
                .status(QUEUED)
                .build();

//        DomainMessage<?> eventMessage = domainMessageFactory
//                .prepareDomainMessageForType(messageType)
//                .withEntity(checkinDTO)
//                .withEntityAndProjection(checkinEntity, CheckinDTO.class) //eu: create projection for json
//                .withCurrentTenant()
//                .withAdditionalProperty("data","test")
//                .build();

        DomainMessage<?> eventMessage = DomainMessage.builder(messageType)
                .tenantId(1L)
                .build(eventPayload);

        String messageKeyForKafkaPartition = String.valueOf(hotelDTO.getDomainEntityId());
        
        kafkaMessagePublisher.publishMessageToPartition(messageKeyForKafkaPartition, eventMessage);
        log.info("Published message {}", eventMessage);
    }

    
    
}
