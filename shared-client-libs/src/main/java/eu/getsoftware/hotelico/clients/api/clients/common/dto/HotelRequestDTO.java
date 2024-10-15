package eu.getsoftware.hotelico.clients.api.clients.common.dto;

import eu.getsoftware.hotelico.clients.common.domain.IDomainResponseDTO;

public record HotelRequestDTO(
        long entityId,
        String name
) implements IDomainResponseDTO {
}
