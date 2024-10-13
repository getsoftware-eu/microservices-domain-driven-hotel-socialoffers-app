package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto;

import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO;
import lombok.Getter;

import java.util.Date;

@Getter
public record CheckinRequestDTO(
        long customerId,
        long hotelId,
        Date checkinFrom,
        Date checkinTo
) implements IDomainRequestDTO
{
        public boolean validateCheckinDates() {
                return checkinFrom < checkinTo;
        }
}

