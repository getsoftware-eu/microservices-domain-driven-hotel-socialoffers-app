package eu.getsoftware.hotelico.hotelapp.application.checkin.port.out;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;

public interface GPSValidationHandler {
   
    boolean checkLastCustomerLocationDiffToHotelById(CustomerDomainEntityId customerId, HotelDomainEntityId hotelId, int distanceKm);

}
