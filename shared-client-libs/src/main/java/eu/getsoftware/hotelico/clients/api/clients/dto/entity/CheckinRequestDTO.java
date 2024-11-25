package eu.getsoftware.hotelico.clients.api.clients.dto.entity;

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
                validateBusinessLogic(checkinFrom, checkinTo);
        }

        public void validateBusinessLogic(Date checkinFrom, Date checkinTo) {
               
                if (checkinTo.after(getMaxDate(MAX_YEAR_OFFSET))) {
                        throw new IllegalArgumentException("checkin Date is only in next " + MAX_YEAR_OFFSET + " years");
                }
                // eu: Pre-Constructor : use here only constructor params!!, fields are not available (checkinFrom, checkinTo)
                if(!checkinDatesAreValid(checkinFrom, checkinTo))
                {
                        throw new IllegalArgumentException("please correct your checkin dates information.");
                }

                if(checkinTo==null || checkinToIsInPast(checkinTo)) {
                        throw new IllegalArgumentException("Checkin Date is wrong or in past");
                }
        }
        
        //only get param from constructor, no field access
        public boolean checkinToIsInPast(Date checkinTo) {
                return checkinTo.before(new Date());
        }

        //only get param from constructor, no field access
        public boolean checkinDatesAreValid(Date checkinFrom, Date checkinTo) {
                return checkinFrom.before(checkinTo);
        }
        
        @org.jetbrains.annotations.NotNull
        private static Date getMaxDate(int max_year_offset) {
                Date maxDate = new Date();
                maxDate.setYear(new Date().getYear() + max_year_offset);
                return maxDate;
        }

       
}

