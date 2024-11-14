//package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging.rabbit;
//
//import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging.CheckinMessagePublisher;
//import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.repository.CheckinRepository;
//import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinDTO;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * central subscriber class
// * eu: builds from payload our local (re-created) instance for persisting it (partial) updated state in repository
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//@RabbitListener(queues = "checkin.queue", id = "listener")
//public class CheckinRabbitMessageSubscriber /*implements DomainMessageSubscriber*/ {
//    
//    private static final String TYPE = "checkin";
//    
//    private final CheckinRepository checkinRepository;
//    
////    @DomainMessageHandler("checkin.checkin.created.event")
//    @RabbitHandler //to consume multiple data type payloads from the same queue
//    public void createCheckin(DomainMessage<CheckinSendEventPayload> message) {
//        CheckinSendEventPayload payload = message.getPayload();
//
//        {
//            log.info("Processing event {}", message.getMessageType());
//            CheckinDTO checkin = toCheckin(payload).build();
//            checkinRepository.save(checkin);
//        }
//    }
//
////    @DomainMessageHandler("checkin.checkin.updated.event")
//    @RabbitHandler //eu:to consume multiple data type payloads from the same queue
//    public void updateCheckin(DomainMessage<CheckinSendEventPayload> message) {
//        CheckinSendEventPayload payload = message.getPayload();
//
//        {
//            log.info("Processing event {}", message.getMessageType());
//            CheckinDTO checkin = toCheckin(payload).build();
//            checkinRepository.partialUpdateCheckin(checkin);
//        }
//    } 
//    
////    @DomainMessageHandler("checkin.checkin.deleted.event")
//    @RabbitHandler
//    public void deleteCheckin(DomainMessage<CheckinSendEventPayload> message) {
//        CheckinSendEventPayload payload = message.getPayload();
//
//        {
//            log.info("Processing event {}", message.getMessageType());
//            checkinRepository.delete(payload.getId());
//        }
//    }
//
//    /**
//     * Re-create an entity from received Event-Payload
//     * @param payload
//     * @return
//     */
//    private CheckinDTO.CheckinDTOBuilder toCheckin(CheckinSendEventPayload payload) {
//        return CheckinDTO.builder()
//                .initId(payload.getId())
//                .from(paylod.getfrom())
//                .to(paylod.getTo())
//                .hotelId(payload.getHoteId())
//                .customerId(payload.getCustomerId());
//    }
//
//    private final TenantAccessor tenantAccessor;
//
////    @DomainMessageHandler("checkin.checkin.created.event")
//    @RabbitHandler
//    public void createProduct(DomainMessage<CheckinSendEventPayload> message) {
//        CheckinSendEventPayload payload = message.getPayload();
//        withMdc(CHECKIN, payload.getId()).execute(() -> {
//            log.info("Processing event {}", message.getMessageType());
//            var checkin = toCheckin(payload).build();
//            checkinRepository.index(checkin);
//        });
//    }
//}
//
