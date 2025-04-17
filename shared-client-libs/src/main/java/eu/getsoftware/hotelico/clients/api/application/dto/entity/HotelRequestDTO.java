package eu.getsoftware.hotelico.clients.api.application.dto.entity;

import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import eu.getsoftware.hotelico.clients.common.domain.IDomainResponseDTO;

public record HotelRequestDTO(
        EntityIdentifier entityId,
        String name
) implements IDomainResponseDTO {
}
