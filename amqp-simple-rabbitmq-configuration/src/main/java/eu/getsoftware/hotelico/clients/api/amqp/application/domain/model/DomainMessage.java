package eu.getsoftware.hotelico.clients.api.amqp.application.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.util.Preconditions.checkNotNull;

@AllArgsConstructor
public class DomainMessage<T extends DomainMessagePayload> {
    
    @NotNull
    @Getter
    private final String messageType;
    
    @NotNull
    private final LocalDateTime timestamp;

    @Nullable
    private final Long tenantId;

    @NotNull
    @Getter
    private final T payload;

    @NotNull
    @JsonInclude(NON_EMPTY)
    private Collection<DiffItem> changeSet = emptyList();
    
    private final long messageId;
    


    //Поля correlationId и causationId поддерживают отслеживание и отладку в распределенных системах.
    private final long correlationId;
    private final long causationId;
    

    

    


    private DomainMessage() {
        // needed by Jackson
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("messageType", messageType) //
                .add("timestamp", timestamp) //
                .add("tenantId", tenantId) //
                .add("changeSet", changeSet) //
                .add("payload", payload) //
                .toString();
    }

    @NotNull
    public static <T extends DomainMessagePayload> DomainMessageBuilder<T> builder(@NotNull String messageType) {
        return new DomainMessageBuilder<>(messageType);
    }

    /**
     * The message consumers might not be interested in all information send out by the message producer.
     * Thus their payload will contain only a sub-set of the attributes send out. The intended usage of this
     * method is on the consumer side. It provides a convenient way to check whether attributes of its payload are affected
     * by the attributes in the change set.
     *
     * @return {@code true} if one of the elements in the change set is contained in the domain message payload,
     * otherwise {@code false}.
     */
    @JsonIgnore
    public boolean isChangeSetRelevantForPayload() {
        Set<String> possiblePathNames = Arrays.stream(BeanUtils.getPropertyDescriptors(payload.getClass()))
                .map(PropertyDescriptor::getName)
                .map(property -> "/" + property)
                .collect(toSet());
        return changeSet.stream()
                .map(DiffItem::getPath)
                .anyMatch(possiblePathNames::contains);
    }

    public static class DomainMessageBuilder<T extends DomainMessagePayload> {

        private final String messageType;

        private Long tenantId;

        private LocalDateTime timestamp = LocalDateTime.now();

        private Collection<DiffItem> changeSet = emptyList();

        @NotNull
        private DomainMessageBuilder(@NotNull String messageType) {
            this.messageType = checkNotNull(messageType);
        }

        @NotNull
        public DomainMessageBuilder<T> timestamp(@NotNull LocalDateTime timestamp) {
            this.timestamp = checkNotNull(timestamp);
            return this;
        }

        @NotNull
        public DomainMessageBuilder<T> tenantId(@Nullable Long tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        @NotNull
        public DomainMessageBuilder<T> changeSet(@Nullable Collection<DiffItem> changeSet) {
            this.changeSet = changeSet;
            return this;
        }

        @NotNull
        public DomainMessage<T> build(@NotNull T payload) {
            //** requireJsonTypeInformation(checkNotNull(payload).getClass()); **/
            //final 
            DomainMessage<T> domainMessage = new DomainMessage<>(messageType, timestamp, tenantId, payload, changeSet, null, null, null);
//            domainMessage.messageType = this.messageType;
//            domainMessage.timestamp = this.timestamp;
//            domainMessage.tenantId = this.tenantId;
//            domainMessage.payload = payload;
//            domainMessage.changeSet = changeSet;
            return domainMessage;
        }
}
