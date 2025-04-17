package eu.getsoftware.hotelico.clients.api.application.dto.entity;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import eu.getsoftware.hotelico.clients.common.domain.IDomainResponseDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CheckinDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Builder
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
