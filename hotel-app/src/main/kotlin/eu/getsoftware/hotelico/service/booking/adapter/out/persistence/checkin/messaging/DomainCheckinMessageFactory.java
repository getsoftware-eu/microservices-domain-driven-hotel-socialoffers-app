package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessage;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.domainMessage.DomainMessagePayload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DomainCheckinMessageFactory {
    
    public DomainMessage<?> create(String messageType, CheckinUseCaseDTO checkinDTO) {

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
