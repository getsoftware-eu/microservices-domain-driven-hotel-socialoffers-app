package eu.getsoftware.hotelico.hotelapp.application.checkin.port.out;

public interface GPSValidationHandler {
   
    boolean checkLastCustomerLocationDiffToHotelById(long customerId, long hotelId, int distanceKm);

}
