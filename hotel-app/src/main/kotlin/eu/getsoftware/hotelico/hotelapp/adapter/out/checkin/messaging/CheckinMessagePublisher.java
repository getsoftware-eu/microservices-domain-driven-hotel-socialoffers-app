package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging;

import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto.CheckinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckinMessagePublisher {
    
    private final DomainMessagePublisher domainMessagePublisher;
    private final DomainMessageFactory domainMessageFactory;
    
    public void publishCheckinCreatedEvent(CheckinDTO checkinDTO){
        publishMessage("checkin.checkin.created.event", checkinDTO);
    }
    
    public void publishCheckinDeletedEvent(CheckinDTO checkinDTO){
        publishMessage("checkin.checkin.deleted.event", checkinDTO);
    }

    /**
     * only private execution!!
     * @param messageType
     * @param checkinDTO
     */
    private void publishMessage(String messageType, CheckinDTO checkinDTO) {

        // DomainMessage<ChatSendEventMessage> domainMessage = domainChatMessageFactory.create(messageType, eventMessage);
       
        DomainMessage<CheckinAggregatePayload> eventMessage = domainMessageFactory
                .prepareDomainMessageForType(messageType)
                .withEntity(checkinDTO)
                .withCurrentTenant()
                .withAdditionalProperty("data","test")
                .build();

        domainMessagePublisher.publish(eventMessage);
        log.info("Published message {}", eventMessage);
    }
    
}
