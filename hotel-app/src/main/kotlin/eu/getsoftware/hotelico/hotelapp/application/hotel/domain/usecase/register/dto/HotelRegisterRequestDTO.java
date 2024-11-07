package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.usecase.register.dto;

import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

/**
 * eu: RequestDTO contains all validation on Parameters (DDD style)
 * 
 * Domain and ValueObject contains max own validation information! Not spread to other services!!!
 *  
 * client just can't create an invalid request+++ :)
 *  
 * @param requesterId
 * @param name
 */
@Getter
public record HotelRegisterRequestDTO(
        @NotNull String hotelCode,
        @NotNull long requesterId,
        String name
) implements IDomainRequestDTO
{
        public static final int MAX_YEAR_OFFSET = 3;

        //eu: ensure that the record objects are created in a VALID STATE.
        public HotelRegisterRequestDTO
        {
                if(hotelCode()<=0)
                {
                   throw new IllegalArgumentException("no code.");
                }
        }
        
        @org.jetbrains.annotations.NotNull
        private static Date getMaxDate(int max_year_offset) {
                Date maxDate = new Date();
                maxDate.setYear(new Date().getYear() + max_year_offset);
                return maxDate;
        }

       
}

