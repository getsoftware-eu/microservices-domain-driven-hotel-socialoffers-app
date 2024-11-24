package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CheckinDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.domainMessage.DomainMessagePayload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DomainCheckinMessageFactory {
    
    public DomainMessage<?> create(String messageType, CheckinDTO checkinDTO) {

        DomainMessagePayload payload = CheckinUpdatedEventPayload.builder()
                .customerId(checkinDTO.getCustomerId())
                .hotelId(checkinDTO.getHotelId())
                .build();
                
        return DomainMessage.builder(messageType)
                .tenantId(1L)
                .timestamp(LocalDateTime.now())
                .build(payload);
        
    }
}
