package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.amqp.application.domain.model.DomainMessage;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto.CheckinDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.collect.Maps.newLinkedHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckinMessagePublisher {

    private static final String DEFAULT_EXCHANGE = "";
    private static final String CHECKIN_CREATED_EVENT_TYPE_NAME = "checkin.checkin.created.event";
    private static final String CHECKIN_UPDATED_EVENT_TYPE_NAME = "checkin.checkin.updated.event";
    private static final String CHECKIN_DELETED_EVENT_TYPE_NAME = "checkin.checkin.deleted.event";

    @NonNull
    private final DomainMessagePublisher domainMessagePublisher;
    private final DomainMessageFactory domainMessageFactory;

    @NonNull
    private final EntityDomainMessageFactory entityMessageFactory;
    @NonNull
    private final AmqpTemplate amqpTemplate;
    @NonNull
    private final StorageApiUriBuilder storageApiUriBuilder;
    @NonNull
    private ShopRefRepository shopRefRepository;

    public void publishCheckinUpdatedEvent(CheckinDTO checkinDTO){
        publishMessage(CHECKIN_UPDATED_EVENT_TYPE_NAME, checkinDTO);
    }

    public void publishCheckinDeletedEvent(CheckinDTO checkinDTO){
        publishMessage(CHECKIN_DELETED_EVENT_TYPE_NAME, checkinDTO);
    }

    /**
     * only private execution!!
     * @param messageType
     * @param checkinDTO
     */
    private void publishMessage(String messageType, CheckinDTO checkinDTO) {

        // DomainMessage<ChatSendEventMessage> domainMessage = domainChatMessageFactory.create(messageType, eventMessage);

        DomainMessage<?> eventMessage = domainMessageFactory
                .prepareDomainMessageForType(messageType)
//                .withEntity(checkinDTO)
                .withEntityAndProjection(checkinEntity, CheckinDTO.class) //eu: create projection for json
                .withCurrentTenant()
                .withAdditionalProperty("data","test")
                .build();

        domainMessagePublisher.publish(eventMessage);
        log.info("Published message {}", eventMessage);
    }
    
    public void publishCheckinCreatedEvent(CheckinDTO checkinDTO){
        DomainMessage<?> message = buildMessage(CHECKIN_CREATED_EVENT_TYPE_NAME, checkinDTO, false);
        publishMessage(message);
        
//        publishMessage(CHECKIN_CREATED_EVENT_TYPE_NAME, checkinDTO);
    }

    public void publishProductUpdatedEvent(CheckinDTO checkinDTO) {
        boolean temporarilyExcludeChangeSetAsHotFix = false;
        DomainMessage<?> message = buildMessage(CHECKIN_UPDATED_EVENT_TYPE_NAME, checkinDTO, temporarilyExcludeChangeSetAsHotFix);
        publishMessage(message);
    }

    public void publishProductDeletedEvent(CheckinDTO checkinDTO) {
        DomainMessage<?> message = buildMessage(CHECKIN_DELETED_EVENT_TYPE_NAME, checkinDTO, false);
        publishMessage(message);
    }

    

    private DomainMessage<?> buildMessage(String messageType, CheckinDTO checkinDTO, boolean includeChangeSet) {

        Map<String, Object> additionalProperties = newLinkedHashMap();

        if (checkinDTO.getDefaultImage() != null) {
            additionalProperties.put("defaultImageDataUri", toRelativeUri(product.getDefaultImage()));
        }

        ChangeSetStep changeSetStep = entityMessageFactory
                .prepareDomainMessageForType(messageType)
                .withEntityAndProjection(checkinDTO, CheckinMessagingProjection.class)
                .withPayloadType("product")
                .withCurrentTenant();

        BuildStep buildStep = includeChangeSet ? changeSetStep.withChangeSet() : changeSetStep.withoutChangeSet();

        return buildStep
                .withStandardContext()
                .withContextProperty("locale", checkinDTO.getCurrentLocale())
                .withContextProperty("shopTaxModel", shopRefRepository.getMandatoryCurrent().getTaxModel())
                .withAdditionalProperties(additionalProperties)
                .build();
    }
}
    
}
