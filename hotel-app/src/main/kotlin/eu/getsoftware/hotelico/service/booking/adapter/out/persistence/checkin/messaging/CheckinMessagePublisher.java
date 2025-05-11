package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.service.messaging.KafkaMessagePublisher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessagePayloadStatus.QUEUED;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckinMessagePublisher {

    private static final String DEFAULT_EXCHANGE = "";
    private static final String CHECKIN_CREATED_EVENT_TYPE_NAME = "checkin.checkin.created.event";
    private static final String CHECKIN_UPDATED_EVENT_TYPE_NAME = "checkin.checkin.updated.event";
    private static final String CHECKIN_DELETED_EVENT_TYPE_NAME = "checkin.checkin.deleted.event";

    @NonNull
    private final KafkaMessagePublisher kafkaMessagePublisher;

    private DomainCheckinMessageFactory domainCheckinMessageFactory;

    public void publishCheckinCreatedEvent(CheckinUseCaseDTO checkinDTO){
        publishMessage(CHECKIN_CREATED_EVENT_TYPE_NAME, checkinDTO);
    }
    
    public void publishCheckinUpdatedEvent(CheckinUseCaseDTO checkinDTO){
        publishMessage(CHECKIN_UPDATED_EVENT_TYPE_NAME, checkinDTO);
    }

    public void publishCheckinDeletedEvent(CheckinUseCaseDTO checkinDTO){
        publishMessage(CHECKIN_DELETED_EVENT_TYPE_NAME, checkinDTO);
    }

    /**
     * only private execution!!
     * @param messageType
     * @param checkinDTO
     */
    private void publishMessage(String messageType, CheckinUseCaseDTO checkinDTO) {

        CustomerDomainEntityId customerEntityId = new CustomerDomainEntityId(String.valueOf(checkinDTO.getCustomerId()));

        CheckinUpdatedEventPayload eventPayload = CheckinUpdatedEventPayload.builder()
                .entityId(checkinDTO.getInitId())
                .status(QUEUED)
                .build();
        
//        DomainMessage<?> eventMessage = domainMessageFactory
//                .prepareDomainMessageForType(messageType)
//                .withEntity(checkinDTO)
//                .withEntityAndProjection(checkinEntity, CheckinDTO.class) //eu: create projection for json
//                .withCurrentTenant()
//                .withAdditionalProperty("data","test")
//                .build();
        
        DomainMessage<?> eventMessage = DomainMessage.builder(messageType)
                .tenantId(1L)
                .build(eventPayload);

//        domainMessagePublisher.publishChatSentEvent(messageType, eventMessage);
        kafkaMessagePublisher.publishMessageToPartition(customerEntityId.uuidValue(), eventMessage);
        log.info("Published message {}", eventMessage);
    }

    
    
    

//    public void publishProductUpdatedEvent(CheckinDTO checkinDTO) {
//        boolean temporarilyExcludeChangeSetAsHotFix = false;
//        DomainMessage<?> message = buildMessage(CHECKIN_UPDATED_EVENT_TYPE_NAME, checkinDTO, temporarilyExcludeChangeSetAsHotFix);
//        publishMessage(message);
//    }
//
//    public void publishProductDeletedEvent(CheckinDTO checkinDTO) {
//        DomainMessage<?> message = buildMessage(CHECKIN_DELETED_EVENT_TYPE_NAME, checkinDTO, false);
//        publishMessage(message);
//    }

    

//    private DomainMessage<?> buildMessage(String messageType, CheckinDTO checkinDTO, boolean includeChangeSet) {
//
//        Map<String, Object> additionalProperties = newLinkedHashMap();
//
//        if (checkinDTO.getDefaultImage() != null) {
//            additionalProperties.put("defaultImageDataUri", toRelativeUri(product.getDefaultImage()));
//        }
//
////        ChangeSetStep changeSetStep = entityMessageFactory
////                .prepareDomainMessageForType(messageType)
////                .withEntityAndProjection(checkinDTO, CheckinMessagingProjection.class)
////                .withPayloadType("product")
////                .withCurrentTenant();
//
////        BuildStep buildStep = includeChangeSet ? changeSetStep.withChangeSet() : changeSetStep.withoutChangeSet();
//
//        return buildStep
//                .withStandardContext()
//                .withContextProperty("locale", checkinDTO.getCurrentLocale())
//                .withContextProperty("shopTaxModel", shopRefRepository.getMandatoryCurrent().getTaxModel())
//                .withAdditionalProperties(additionalProperties)
//                .build();
//    }
    

}
    
 
