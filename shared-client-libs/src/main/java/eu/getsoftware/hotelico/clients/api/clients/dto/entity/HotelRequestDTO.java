package eu.getsoftware.hotelico.clients.api.clients.dto.entity;

import eu.getsoftware.hotelico.clients.common.domain.IDomainResponseDTO;

public record HotelRequestDTO(
        long entityId,
        String name
) implements IDomainResponseDTO {
}
