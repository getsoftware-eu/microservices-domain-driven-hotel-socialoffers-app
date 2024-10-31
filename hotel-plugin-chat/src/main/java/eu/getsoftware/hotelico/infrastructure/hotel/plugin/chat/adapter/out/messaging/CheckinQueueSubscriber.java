package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * eu: This listener reacts on new events only in checkin Query.
 * For every incoming checkin-event we have own "Processing Manager" actions(commands)!
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(queues = "checkin.queue", id = "listener")
public class CheckinQueueSubscriber implements DomainMessageSubscriber {
    
    private static final String TYPE = "checkin";

    /**
     * extra "prozess manager" for checkin-events for Chat-Domain-Microservice
     */
    ChatCheckinProzessManager chatCheckinProzessManager;
    
//    @DomainMessageHandler("checkin.checkin.created.event")
    @RabbitHandler //to consume multiple data type payloads from the same queue
    public void createCheckin(DomainMessage<CheckinAggregatePayload> message) {
        CheckinAggregatePayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            chatCheckinProzessManager.createWellcomeChatMessage(payload.checkinCustomer, payload.hotelId);
        }
    }

//    @DomainMessageHandler("checkin.checkin.updated.event")
    @RabbitHandler //eu:to consume multiple data type payloads from the same queue
    public void updateCheckin(DomainMessage<CheckinAggregatePayload> message) {
        CheckinAggregatePayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
//            CheckinDTO checkin = toCheckin(payload).build();
//            checkinRepository.partialUpdateCheckin(checkin);
        }
    } 
    
//    @DomainMessageHandler("checkin.checkin.deleted.event")
    @RabbitHandler
    public void deleteCheckin(DomainMessage<CheckinAggregatePayload> message) {
        CheckinAggregatePayload payload = message.getPayload();

        {
            log.info("Processing event {}", message.getMessageType());
            chatCheckinProzessManager.createClosingChatMessage(payload.checkinCustomer, payload.hotelId);
        }
    }

    /**
     * Re-create an entity from received Event-Payload
     * @param payload
     * @return
     */
    private CheckinBuilder toCheckin(CheckinAggregatePayload payload) {
        return CheckinDTO.builder()
                .initId(payload.getId())
                .from(paylod.getfrom())
                .to(paylod.getTo())
                .hotelId(payload.getHoteId())
                .customerId(payload.getCustomerId());
    }

    private final TenantAccessor tenantAccessor;

//    @DomainMessageHandler("checkin.checkin.created.event")
    @RabbitHandler
    public void createProduct(DomainMessage<CheckinAggregatePayload> message) {
        CheckinAggregatePayload payload = message.getPayload();
        withMdc(CHECKIN, payload.getId()).execute(() -> {
            log.info("Processing event {}", message.getMessageType());
            var checkin = toCheckin(payload).build();
            checkinRepository.index(checkin);
        });
    }
}

