package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.CustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.repository.CheckinRepository;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckinKafkaSubscriber {

    private final CheckinRepository checkinRepository;

    @KafkaListener(topics = {"checkin.checkin.created.event", 
                             "checkin.checkin.updated.event"}, 
                   groupId = "eu_group_1")
     void listener(DomainMessage<?> data){
        System.out.println("Kafka listener : " + data.toString());
    }

    @KafkaListener(topics = {"checkin.checkin.created.event"}, groupId = "eu_group_1")
    public void createCheckin(DomainMessage<CheckinMessagePublisher.CheckinSendEventPayload> message) {
        CheckinMessagePublisher.CheckinSendEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            
            CheckinDTO checkin = toCheckinDTO(payload).build();
            CustomerHotelCheckin entity = checkinRepository.findById(checkin.getInitId()).orElseThrow(()-> new RuntimeException("not found"));

            checkinRepository.save(entity);
        }
    }

    //    @DomainMessageHandler("checkin.checkin.updated.event")
    @KafkaListener(topics = {"checkin.checkin.updated.event"}, groupId = "eu_group_1")
    public void updateCheckin(DomainMessage<CheckinMessagePublisher.CheckinSendEventPayload> message) {
        CheckinMessagePublisher.CheckinSendEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            CheckinDTO checkin = toCheckinDTO(payload).build();
//            checkinRepository.partialUpdateCheckin(checkin);

             CustomerHotelCheckin entity = checkinRepository.findById(checkin.getInitId()).orElseThrow(()-> new RuntimeException("not found"));
            
            checkinRepository.save(entity);
        }
    }

    //    @DomainMessageHandler("checkin.checkin.deleted.event")
    @KafkaListener(topics = {"checkin.checkin.deleted.event"}, groupId = "eu_group_1")
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
