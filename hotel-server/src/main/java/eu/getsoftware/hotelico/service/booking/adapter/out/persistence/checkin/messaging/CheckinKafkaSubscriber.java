package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessage;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.mapper.CheckinDtoMapper;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.CheckinDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckinKafkaSubscriber {

    private final CheckinRepository checkinRepository;
    private final CheckinDtoMapper checkinDtoMapper;

    @KafkaListener(topics = {"checkin.checkin.created.event", 
                             "checkin.checkin.updated.event"}, 
                   groupId = "checkin.notification.processor.dev")
     void listener(DomainMessage<?> data){
        System.out.println("Kafka listener : " + data.toString());
    }

    @KafkaListener(topics = {"checkin.checkin.created.event"}, groupId = "checkin.notification.processor.dev")
    public void createCheckin(DomainMessage<CheckinUpdatedEventPayload> message) {
        CheckinUpdatedEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            
            if(payload instanceof CheckinUpdatedEventPayload) {
                CheckinUpdatedEventPayload checkinPayload = payload;
                
                CheckinUseCaseDTO checkinDTO = toCheckinDTO(checkinPayload);//.build();
                
                if(checkinRepository.existsByDomainEntityIdValue(checkinDTO.getInitId()))
                    throw new RuntimeException("not found");

                CheckinDBEntity entity = checkinDtoMapper.toEntity(checkinDTO);
                checkinRepository.save(entity);

                log.info("Received CheckinUpdatedEventPayload with entityId: {}", checkinPayload.getEntityId());
            } else {
                log.warn("Received unexpected payload type: {}", payload.getClass().getName());
            }
            
        }
    }

    //    @DomainMessageHandler("checkin.checkin.updated.event")
    @KafkaListener(topics = {"checkin.checkin.updated.event"}, groupId = "checkin.notification.processor.dev")
    public void updateCheckin(DomainMessage<CheckinUpdatedEventPayload> message) {
        CheckinUpdatedEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            CheckinUseCaseDTO checkinDTO = toCheckinDTO(payload);//.build();
//            checkinRepository.partialUpdateCheckin(checkinDTO);

             CheckinDBEntity entity = checkinRepository.findByDomainEntityIdValue(checkinDTO.getInitId()).orElseThrow(()-> new RuntimeException("not found"));
             
             applyDtoUpdates(entity, checkinDTO);
             
             checkinRepository.save(entity);
        }
    }

    private void applyDtoUpdates(CheckinRootDomainEntity entity, CheckinUseCaseDTO checkin) {
        //TODO
    }

    //    @DomainMessageHandler("checkin.checkin.deleted.event")
    @KafkaListener(topics = {"checkin.checkin.deleted.event"}, groupId = "checkin.notification.processor.dev")
    public void deleteCheckin(DomainMessage<CheckinUpdatedEventPayload> message) {
         var payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            checkinRepository.deleteByDomainEntityIdValue(((CheckinUpdatedEventPayload)payload).getEntityId());
        }
    }

    /**
     * Re-create an entity from received Event-Payload
     *
     * @param payload
     * @return
     */
    private CheckinUseCaseDTO toCheckinDTO(CheckinUpdatedEventPayload payload) {
        return CheckinUseCaseDTO.builder()
                .initId(payload.getEntityId())
                .checkinFrom(payload.getCheckinFrom())
                .checkinTo(payload.getCheckinTo())
                .hotelId(payload.getHotelId())
                .customerId(payload.getCustomerId())
                .build();
    }
    
}
