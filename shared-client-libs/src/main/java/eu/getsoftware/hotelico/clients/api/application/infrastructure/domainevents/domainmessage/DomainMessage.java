package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

//@Builder - eu: manual Builder
//@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DomainMessage<T extends DomainMessagePayload> {

    @NotNull
    private final String messageType; //checkin.checkin.updated.event

    @NotNull
    private final LocalDateTime timestamp;

    @Nullable
    private final Long tenantId;

    @NotNull
    private final T payload;

//    @NotNull
//    @JsonInclude(NON_EMPTY)
//    private Collection<DiffItem> changeSet = emptyList();

    private final long messageId;

    //Поля correlationId и causationId поддерживают отслеживание и отладку в распределенных системах.
    private final long correlationId;
    private final long causationId;

    @NotNull
    public T getPayload() {
        return payload;
    }

    public String getMessageType() {
        return messageType;
    }
    
//    @Override
//    public String toString() {
//        return MoreObjects.toStringHelper(this) //
//                .add("messageType", messageType) //
//                .add("timestamp", timestamp) //
//                .add("tenantId", tenantId) //
//                .add("changeSet", changeSet) //
//                .add("payload", payload) //
//                .toString();
//    }
    
    /**
     * The message consumers might not be interested in all information send out by the message producer.
     * Thus their payload will contain only a sub-set of the attributes send out. The intended usage of this
     * method is on the consumer side. It provides a convenient way to check whether attributes of its payload are affected
     * by the attributes in the change set.
     *
     * @return {@code true} if one of the elements in the change set is contained in the domain message payload,
     * otherwise {@code false}.
     */
//    @JsonIgnore
//    public boolean isChangeSetRelevantForPayload() {
//        Set<String> possiblePathNames = Arrays.stream(BeanUtils.getPropertyDescriptors(payload.getClass()))
//                .map(PropertyDescriptor::getName)
//                .map(property -> "/" + property)
//                .collect(toSet());
//        return changeSet.stream()
//                .map(DiffItem::getPath)
//                .anyMatch(possiblePathNames::contains);
//    }

    
}
