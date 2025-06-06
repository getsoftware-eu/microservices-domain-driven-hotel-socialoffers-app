package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.messaging;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.CheckinDomainEvent;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessage;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.domainmessage.DomainMessagePayload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DomainCheckinMessageFactory {
    
    public DomainMessage<?> create(String messageType, CheckinUseCaseDTO checkinDTO) {

        DomainMessagePayload payload = CheckinUpdatedEventPayload.builder()
                .customerId(checkinDTO.getCustomerId())
                .hotelId(checkinDTO.getHotelId())
                .build();
                
        return CheckinDomainEvent.builder()
                .messageType(messageType)
                .tenantId(1L)
                .timestamp(LocalDateTime.now())
                .payload(payload)
                .build();
        
    }
}
