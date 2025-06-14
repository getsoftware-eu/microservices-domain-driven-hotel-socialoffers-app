package eu.getsoftware.hotelico.clients.api.application.dto.entity;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import eu.getsoftware.hotelico.clients.common.domain.IDomainResponseDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CheckinDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class CheckinUseCaseDTO implements IDomainResponseDTO {

    CheckinDomainEntityId initId;
    CheckinDomainEntityId entityId;
    CustomerDomainEntityId customerId;
    
    @Setter
    HotelDomainEntityId hotelId;
    
    @NotNull
    Date checkinFrom;
    Date checkinTo;

    @Override
    public EntityIdentifier entityId() {
        return entityId;
    }
}
