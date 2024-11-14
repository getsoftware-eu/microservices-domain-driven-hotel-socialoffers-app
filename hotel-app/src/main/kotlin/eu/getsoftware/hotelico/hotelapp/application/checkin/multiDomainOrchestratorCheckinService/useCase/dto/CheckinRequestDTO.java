package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto;

import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * eu: RequestDTO contains all validation on Parameters (DDD style)
 * 
 * Domain and ValueObject contains max own validation information! Not spread to other services!!!
 *  
 * client just can't create an invalid request+++ :)
 *  
 * @param customerId
 * @param hotelId
 * @param checkinFrom
 * @param checkinTo
 * @param requesterId
 * @param name
 */
public record CheckinRequestDTO(
        @NotNull CustomerDomainEntityId customerId,
        @NotNull HotelDomainEntityId hotelId,
        Date checkinFrom,
        Date checkinTo,
        @NotNull long requesterId,
        String name
) implements IDomainRequestDTO
{
        public static final int MAX_YEAR_OFFSET = 3;

        //eu: ensure that the record objects are created in a VALID STATE.
        public CheckinRequestDTO
        {
                if(customerId()==null)
                {
                   throw new IllegalArgumentException("no user for checkin.");
                }

                if (checkinTo.after(getMaxDate(MAX_YEAR_OFFSET))) {
                        throw new IllegalArgumentException("checkin Date is only in next " + MAX_YEAR_OFFSET + " years");
                }

                if(!checkinDatesAreValid())
                {
                   throw new IllegalArgumentException("please correct your checkin dates information.");
                }

                if(hotelId!=null && (checkinTo==null || checkinToIsInPast())) {
                        throw new IllegalArgumentException("Checkin Date is wrong or in past");
                }
        }

        public boolean checkinToIsInPast() {
                return checkinTo().before(new Date());
        }

        public boolean checkinDatesAreValid() {
                return checkinFrom.before(checkinTo);
        }
        
        @org.jetbrains.annotations.NotNull
        private static Date getMaxDate(int max_year_offset) {
                Date maxDate = new Date();
                maxDate.setYear(new Date().getYear() + max_year_offset);
                return maxDate;
        }

       
}

