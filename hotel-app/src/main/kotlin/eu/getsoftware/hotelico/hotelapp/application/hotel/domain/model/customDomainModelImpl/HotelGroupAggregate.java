package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.customDomainModelImpl;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.innerDomainService.domainEvents.DomainEventRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.innerDomainService.domainEvents.IDomainEventsProducerService;
import org.modelmapper.ModelMapper;

/**
 * All update Facade entry class
 */
public class HotelGroupAggregate
{
    private HotelDomainEntity hotelDomainEntity;
    private IDomainEventsProducerService<HotelEvent> domainEventsProducerService;
    private ModelMapper modelMapper;

    void setHotelDomainAction(long hotelId){
        DomainEventRequestDTO dto = modelMapper.map(hotelDomainEntity, DomainEventRequestDTO.class);
        domainEventsProducerService.sendDomainNotification(dto, HotelEvent.EVENT_CHECKIN);
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
