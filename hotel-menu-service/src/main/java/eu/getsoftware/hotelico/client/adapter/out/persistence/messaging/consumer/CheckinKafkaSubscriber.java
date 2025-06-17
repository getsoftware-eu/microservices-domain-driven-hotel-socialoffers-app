package eu.getsoftware.hotelico.client.adapter.out.persistence.messaging.consumer;

import eu.getsoftware.hotelico.client.application.port.in.process.MenuProcessManagerService;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckinKafkaSubscriber {

    private final MenuProcessManagerService menuProcessManager;
    
    @KafkaListener(topics = {"checkin.checkin.created.event", 
                             "checkin.checkin.updated.event"}, 
                   groupId = "eu_group_1")
     void listener(DomainMessage<?> data){
        System.out.println("Kafka listener : " + data.toString());
    }

    @KafkaListener(topics = {"checkin.checkin.created.event"}, groupId = "eu_group_1")
    public void createCheckin(DomainMessage<CheckinUpdatedEventPayload> message) {
        var payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            
            CheckinUseCaseDTO checkinDTO = toCheckin(payload);
//            checkinRepository.save(checkinDTO);
            menuProcessManager.handleCheckinCreated(checkinDTO);
        }
    }

    //    @DomainMessageHandler("checkin.checkin.updated.event")
    @KafkaListener(topics = {"checkin.checkin.updated.event"}, groupId = "eu_group_1")
    public void updateCheckin(DomainMessage<CheckinUpdatedEventPayload> message) {
        var payload = message.getPayload();

        log.info("Processing event {}", message.getMessageType());
        
        CheckinUseCaseDTO checkin = toCheckin(payload);
//            checkinRepository.partialUpdateCheckin(checkin);
        menuProcessManager.handleCheckinUpdated(checkin);

    }

    //    @DomainMessageHandler("checkin.checkin.deleted.event")
    @KafkaListener(topics = {"checkin.checkin.deleted.event"}, groupId = "eu_group_1")
    public void deleteCheckin(DomainMessage<CheckinUpdatedEventPayload> message) {
        var payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
//            checkinRepository.deleteById(payload.getEntityId());
            menuProcessManager.handleCheckinClosed((payload).getEntityId());

        }
    }

    /**
     * Re-create an entity from received Event-Payload
     *
     * @param payload
     * @return
     */
    private CheckinUseCaseDTO toCheckin(CheckinUpdatedEventPayload payload) {
        return CheckinUseCaseDTO.builder()
                .initId(payload.getEntityId())
                .checkinFrom(payload.getCheckinFrom())
                .checkinTo(payload.getCheckinTo())
                .hotelId(payload.getHotelId())
                .customerId(payload.getCustomerId())
                .build();
    }
    
}
