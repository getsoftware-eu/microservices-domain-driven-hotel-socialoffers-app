package eu.getsoftware.hotelico.client.adapter.out.persistence.messaging.consumer.amqp;//package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.messaging;
//
//import eu.getsoftware.hotelico.clients.api.amqp.application.domain.model.DomainMessage;
//import eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.message.CheckinAggregatePayload;
//import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.messaging.service.ChatCheckinProcessManager;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//
///**
// * eu: This listener reacts on new events only in checkin Query.
// * For every incoming checkin-event we have own "Processing Manager" actions(commands)!
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//@RabbitListener(queues = "checkin.queue", id = "listener")
//public class CheckinRabbitMQQueueSubscriber /*implements DomainMessageSubscriber */{
//    
//    private static final String TYPE = "checkin";
//
//    /**
//     * extra "process manager" for checkin-events for Chat-Domain-Microservice
//     */
//    ChatCheckinProcessManager chatCheckinProcessManager;
//    
////    @DomainMessageHandler("checkin.checkin.created.event")
//    @RabbitHandler //to consume multiple data type payloads from the same queue
//    public void createCheckin(DomainMessage<CheckinAggregatePayload> message) {
//        CheckinAggregatePayload payload = message.getPayload();
//
//        {
//            log.info("Processing event {}", message.getMessageType());
//            chatCheckinProcessManager.createWelcomeChatMessage(payload.getCheckinCustomer(), payload.getHotelId());
//        }
//    }
//
////    @DomainMessageHandler("checkin.checkin.updated.event")
//    @RabbitHandler //eu:to consume multiple data type payloads from the same queue
//    public void updateCheckin(DomainMessage<CheckinAggregatePayload> message) {
//        CheckinAggregatePayload payload = message.getPayload();
//
//        {
//            log.info("Processing event {}", message.getMessageType());
////            CheckinDTO checkin = toCheckin(payload).build();
////            checkinRepository.partialUpdateCheckin(checkin);
//        }
//    } 
//    
////    @DomainMessageHandler("checkin.checkin.deleted.event")
//    @RabbitHandler
//    public void deleteCheckin(DomainMessage<CheckinAggregatePayload> message) {
//        CheckinAggregatePayload payload = message.getPayload();
//
//        {
//            log.info("Processing event {}", message.getMessageType());
//            chatCheckinProcessManager.createClosingChatMessage(payload.getCheckinCustomer(), payload.getHotelId());
//        }
//    }
//
//    /**
//     * Re-create an entity from received Event-Payload
//     * @param payload
//     * @return
//     */
//    private CheckinBuilder toCheckin(CheckinAggregatePayload payload) {
//        return CheckinDTO.builder()
//                .initId(payload.getId())
//                .from(payload.getFrom())
//                .to(payload.getTo())
//                .hotelId(payload.getHotelId())
//                .customerId(payload.getCustomerId());
//    }
//
//    private final TenantAccessor tenantAccessor;
//
////    @DomainMessageHandler("checkin.checkin.created.event")
//    @RabbitHandler
//    public void createProduct(DomainMessage<CheckinAggregatePayload> message) {
//        CheckinAggregatePayload payload = message.getPayload();
//        withMdc(CHECKIN, payload.getId()).execute(() -> {
//            log.info("Processing event {}", message.getMessageType());
//            var checkin = toCheckin(payload).build();
//            checkinRepository.index(checkin);
//        });
//    }
//}
//
