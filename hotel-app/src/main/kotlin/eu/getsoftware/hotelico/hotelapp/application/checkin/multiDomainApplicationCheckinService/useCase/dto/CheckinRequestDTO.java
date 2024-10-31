package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.dto;

import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
public record CheckinRequestDTO(
        @NotNull long customerId,
        @NotNull long hotelId,
        Date checkinFrom,
        Date checkinTo,
        @NotNull long requesterId,
        String name
) implements IDomainRequestDTO
{
        
        public CheckinRequestDTO
        {
                if(customerId()<=0)
                {
                   throw new IllegalArgumentException("no user for checkin.");
                }

                if(!validateCheckinDates())
                {
                   throw new IllegalArgumentException("please correct your checkin dates information.");
                }

                if(hotelId()>0 && (checkinTo()==null || checkinToIsInPast())) {
                        throw new IllegalArgumentException("Checkin Date is wrong or in past");
                }
        }

        public boolean checkinToIsInPast() {
                return checkinTo().before(new Date());
        }

        public boolean validateCheckinDates() {
                return checkinFrom.before(checkinTo);
        }
}

