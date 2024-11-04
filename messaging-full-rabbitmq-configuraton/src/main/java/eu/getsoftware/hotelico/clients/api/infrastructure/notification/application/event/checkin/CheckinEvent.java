package eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.event.checkin;

import eu.getsoftware.hotelico.clients.api.amqp.application.domain.model.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerEntityId;

public class CheckinEvent extends DomainMessagePayload {
    
    private final String userId;
    private final String checkinStatus;

    public CheckinEvent(CustomerEntityId customerEntityId, String checkinStatus) {
        this.userId = customerEntityId.toString();
        this.checkinStatus = checkinStatus;
    }

    public String getUserId() {
        return userId;
    }

    public String getCheckinStatus() {
        return checkinStatus;
    }

    @Override
    public String toString() {
        return "CheckinEvent{" +
                "userId='" + userId + '\'' +
                ", checkinStatus='" + checkinStatus + '\'' +
                '}';
    }
}
