package eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import lombok.Getter;

@Getter
public class CheckinEvent extends DomainMessagePayload {
    
    private final String userId;
    private final String checkinStatus;

    public CheckinEvent(CustomerDomainEntityId customerEntityId, String checkinStatus) {
        this.userId = customerEntityId.toString();
        this.checkinStatus = checkinStatus;
    }

    @Override
    public String toString() {
        return "CheckinEvent{" +
                "userId='" + userId + '\'' +
                ", checkinStatus='" + checkinStatus + '\'' +
                '}';
    }
}
