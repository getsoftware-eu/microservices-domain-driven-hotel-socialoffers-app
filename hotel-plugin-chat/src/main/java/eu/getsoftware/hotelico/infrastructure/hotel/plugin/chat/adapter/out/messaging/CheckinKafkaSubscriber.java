package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.messaging;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheckinKafkaSubscriber {

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
            CheckinDTO checkin = toCheckin(payload).build();
            checkinRepository.save(checkin);
        }
    }

    //    @DomainMessageHandler("checkin.checkin.updated.event")
    @KafkaListener(topics = {"checkin.checkin.updated.event"}, groupId = "eu_group_1")
    public void updateCheckin(DomainMessage<CheckinMessagePublisher.CheckinSendEventPayload> message) {
        CheckinMessagePublisher.CheckinSendEventPayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            CheckinDTO checkin = toCheckin(payload).build();
            checkinRepository.partialUpdateCheckin(checkin);
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
    private CheckinDTO.CheckinDTOBuilder toCheckin(CheckinMessagePublisher.CheckinSendEventPayload payload) {
        return CheckinDTO.builder()
                .initId(payload.getEntityId())
                .checkinFrom(payload.getCheckinFrom())
                .checkinTo(payload.getCheckinTo())
                .hotelId(payload.getHoteId())
                .customerId(payload.getCustomerId());
    }
    
}
