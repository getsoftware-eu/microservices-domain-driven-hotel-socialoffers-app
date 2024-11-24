package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload.CheckinEvent;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DomainCheckinMessageFactory {
    
    public DomainMessage<?> create(String messageType, CheckinDTO checkinDTO) {

        CustomerDomainEntityId customerEntityId = new CustomerDomainEntityId(String.valueOf(checkinDTO.getCustomerId()));

        DomainMessagePayload payload = new CheckinEvent(customerEntityId, "QUEUED");

        return DomainMessage.builder(messageType)
                .tenantId(1L)
                .timestamp(LocalDateTime.now())
                .build(payload);
        
    }
}
