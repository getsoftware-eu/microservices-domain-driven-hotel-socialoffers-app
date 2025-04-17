package eu.getsoftware.hotelico.clients.api.infrastructure.notification.application.message;

import eu.getsoftware.hotelico.clients.api.amqp.application.domain.model.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.api.application.common.dto.CustomerDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class CheckinAggregatePayload extends DomainMessagePayload {

    private CustomerDTO checkinCustomer;
    private long initId;
    private long hotelId;

    private Date from;
    private Date to;

    public Long getCustomerId() {
        return checkinCustomer.getId();
    }

    public Long getId() {
        return initId;
    }
}
