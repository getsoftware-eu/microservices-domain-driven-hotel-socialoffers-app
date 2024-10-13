package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

/**
 * All update Facade entry class
 */
public class HotelGroupAggregate
{
    default void setHotelDomainAction(long hotelId){
        domainEventsProducerService.sendDomainNotification(convertToHotelDomainDTO(this), DomainTopicEvent.NEW_CHECKIN);
    }
}
