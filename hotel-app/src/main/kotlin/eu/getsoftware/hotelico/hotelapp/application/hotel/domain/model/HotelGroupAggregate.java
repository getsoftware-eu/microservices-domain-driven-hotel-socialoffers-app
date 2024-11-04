package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelEntityId;

/**
 * All update Facade entry class
 */
public class HotelGroupAggregate
{
    default void setHotelDomainAction(long hotelId){
        domainEventsProducerService.sendDomainNotification(convertToHotelDomainDTO(this), DomainTopicEvent.NEW_CHECKIN);
    }

    /**
     * Entry Point to all Entity setters!!!
     * @return
     */
    public static HotelDomainEntity.HotelDomainEntityBuilder getEntityBuilder(HotelEntityId hotelEntityId){
        return new HotelDomainEntity(hotelEntityId).builder();
    }
}
