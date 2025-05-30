package eu.getsoftware.hotelico.clients.api.application.dto.entity;

import eu.getsoftware.hotelico.clients.common.domain.IDomainRequestDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

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
public record CheckinUseCaseRequestDTO(
        @NotNull CustomerDomainEntityId customerId,
        @NotNull HotelDomainEntityId hotelId,
        LocalDate checkinFrom,
        LocalDate checkinTo,
        @NotNull long requesterId,
        String name
) implements IDomainRequestDTO
{
        public static final int MAX_YEAR_OFFSET = 3;

        //eu: ensure that the record objects are created in a VALID STATE.
        public CheckinUseCaseRequestDTO
        {
                validateBusinessLogic(checkinFrom, checkinTo);
        }

//        public static CheckinUseCaseRequestDTO of(
//                CheckinRequestRestDTO restRequest
//        ) {
//                LocalDate now = LocalDate.now();
//                LocalDate defaultCheckinTo = now.plusDays(3);
//                String defaultName = "Anonymous";
//
//                String customerID = String.valueOf(restRequest.getCustomerId());
//                String hotelID = String.valueOf(restRequest.getHotelId());
//                
//                return new CheckinUseCaseRequestDTO(
//                        new CustomerDomainEntityId(customerID, true),
//                        HotelDomainEntityId.from(hotelID),
//                        now,
//                        defaultCheckinTo,
//                        requesterId,
//                        defaultName
//                );
//        }

        public void validateBusinessLogic(LocalDate checkinFrom, LocalDate checkinTo) {
               
                if (checkinTo.isAfter(getMaxDate(MAX_YEAR_OFFSET))) {
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
        public boolean checkinToIsInPast(LocalDate checkinTo) {
                return checkinTo.isBefore( LocalDate.now() );
        }

        //only get param from constructor, no field access
        public boolean checkinDatesAreValid() {
                return checkinDatesAreValid(checkinFrom, checkinTo);
        }  
        
        public boolean checkinDatesAreValid(LocalDate checkinFrom, LocalDate checkinTo) {
                return checkinFrom.isBefore(checkinTo);
        }
        
        @org.jetbrains.annotations.NotNull
        private static LocalDate getMaxDate(int max_year_offset) {
                LocalDate maxDate = LocalDate.now();
                maxDate.withYear(LocalDate.now().getYear() + max_year_offset);
                return maxDate;
        }

       
}

