package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload.CheckinSendEventPayload;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.CheckinDbEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.application.checkin.domain.CheckinRootDomainEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckinKafkaSubscriber {

    private final CheckinRepository checkinRepository;
    private final ModelMapper modelMapper;

    @KafkaListener(topics = {"checkin.checkin.created.event", 
                             "checkin.checkin.updated.event"}, 
                   groupId = "checkin.notification.processor.dev")
     void listener(DomainMessage<?> data){
        System.out.println("Kafka listener : " + data.toString());
    }

    @KafkaListener(topics = {"checkin.checkin.created.event"}, groupId = "checkin.notification.processor.dev")
    public void createCheckin(DomainMessage<CheckinSendEventPayload> message) {
        CheckinSendEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            
            CheckinDTO checkinDTO = toCheckinDTO(payload).build();
            
            if(checkinRepository.existsById(checkinDTO.getInitId()))
                throw new RuntimeException("not found");

            CheckinDbEntity entity = modelMapper.map(checkinDTO, CheckinDbEntity.class);
            
            checkinRepository.save(entity);
        }
    }

    //    @DomainMessageHandler("checkin.checkin.updated.event")
    @KafkaListener(topics = {"checkin.checkin.updated.event"}, groupId = "checkin.notification.processor.dev")
    public void updateCheckin(DomainMessage<CheckinSendEventPayload> message) {
        CheckinSendEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            CheckinDTO checkinDTO = toCheckinDTO(payload).build();
//            checkinRepository.partialUpdateCheckin(checkinDTO);

             CheckinDbEntity entity = checkinRepository.findById(checkinDTO.getInitId()).orElseThrow(()-> new RuntimeException("not found"));
             
             applyDtoUpdates(entity, checkinDTO);
             
             checkinRepository.save(entity);
        }
    }

    private void applyDtoUpdates(CheckinRootDomainEntity entity, CheckinDTO checkin) {
        //TODO
    }

    //    @DomainMessageHandler("checkin.checkin.deleted.event")
    @KafkaListener(topics = {"checkin.checkin.deleted.event"}, groupId = "checkin.notification.processor.dev")
    public void deleteCheckin(DomainMessage<CheckinSendEventPayload> message) {
        CheckinSendEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            checkinRepository.deleteById(payload.getEntityId());
        }
    }

    /**
     * Re-create an entity from received Event-Payload
     * @param payload
     * @return
     */
    private CheckinDTO.CheckinDTOBuilder toCheckinDTO(CheckinSendEventPayload payload) {
        return CheckinDTO.builder()
                .initId(payload.getEntityId())
                .checkinFrom(payload.getCheckinFrom())
                .checkinTo(payload.getCheckinTo())
                .hotelId(payload.getHotelId())
                .customerId(payload.getCustomerId());
    }
    
}
