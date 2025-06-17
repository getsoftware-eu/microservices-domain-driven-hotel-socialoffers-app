package eu.getsoftware.hotelico.service.booking.application.checkin.port.out;

import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;

public interface GPSValidationHandler {
   
    boolean checkLastCustomerLocationDiffToHotelById(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId, int distanceKm);

}
