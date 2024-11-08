package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.CustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinDTO;
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
    public void createCheckin(DomainMessage<CheckinMessagePublisher.CheckinSendEventPayload> message) {
        CheckinMessagePublisher.CheckinSendEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            
            CheckinDTO checkinDTO = toCheckinDTO(payload).build();
            
            if(checkinRepository.existsById(checkinDTO.getInitId()))
                throw new RuntimeException("not found");

            CustomerHotelCheckin entity = modelMapper.map(checkinDTO, CustomerHotelCheckin.class);
            
            checkinRepository.save(entity);
        }
    }

    //    @DomainMessageHandler("checkin.checkin.updated.event")
    @KafkaListener(topics = {"checkin.checkin.updated.event"}, groupId = "checkin.notification.processor.dev")
    public void updateCheckin(DomainMessage<CheckinMessagePublisher.CheckinSendEventPayload> message) {
        CheckinMessagePublisher.CheckinSendEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            CheckinDTO checkinDTO = toCheckinDTO(payload).build();
//            checkinRepository.partialUpdateCheckin(checkinDTO);

             CustomerHotelCheckin entity = checkinRepository.findById(checkinDTO.getInitId()).orElseThrow(()-> new RuntimeException("not found"));
             
             applyDtoUpdates(entity, checkinDTO);
             
             checkinRepository.save(entity);
        }
    }

    private void applyDtoUpdates(CustomerHotelCheckin entity, CheckinDTO checkin) {
        //TODO
    }

    //    @DomainMessageHandler("checkin.checkin.deleted.event")
    @KafkaListener(topics = {"checkin.checkin.deleted.event"}, groupId = "checkin.notification.processor.dev")
    public void deleteCheckin(DomainMessage<CheckinMessagePublisher.CheckinSendEventPayload> message) {
        CheckinMessagePublisher.CheckinSendEventPayload payload = message.getPayload();

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
    private CheckinDTO.CheckinDTOBuilder toCheckinDTO(CheckinMessagePublisher.CheckinSendEventPayload payload) {
        return CheckinDTO.builder()
                .initId(payload.getEntityId())
                .checkinFrom(payload.getCheckinFrom())
                .checkinTo(payload.getCheckinTo())
                .hotelId(payload.getHotelId())
                .customerId(payload.getCustomerId());
    }
    
}
