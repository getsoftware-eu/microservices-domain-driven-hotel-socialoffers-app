package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.customDomainModelImpl;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelEntityId;

/**
 * All update Facade entry class
 */
public class HotelGroupAggregate
{
    private HotelDomainEntity hotelDomainEntity;
    
    void setHotelDomainAction(long hotelId){
        domainEventsProducerService.sendDomainNotification(convertToHotelDomainDTO(this.hotelDomainEntity), DomainTopicEvent.NEW_CHECKIN);
    }

    /**
     * Entry Point to all Entity setters!!!
     * muss enter hotelEntityId as parameter
     * @return
     */
    public static HotelDomainEntity.HotelDomainEntityBuilder getEntityBuilder(HotelEntityId hotelEntityId){
        return HotelDomainEntity.builder().hotelEntityId(hotelEntityId);
    }
}
