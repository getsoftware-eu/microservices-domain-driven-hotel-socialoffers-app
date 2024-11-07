package eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload.CheckinEvent;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessagePayload;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerEntityId;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DomainCheckinMessageFactory {
    
    public DomainMessage<?> create(String messageType, CheckinDTO checkinDTO) {

        CustomerEntityId customerEntityId = new CustomerEntityId(String.valueOf(checkinDTO.getCustomerId()));

        DomainMessagePayload payload = new CheckinEvent(customerEntityId, "QUEUED");

        return DomainMessage.builder(messageType)
                .tenantId(1L)
                .timestamp(LocalDateTime.now())
                .build(payload);
        
    }
}
