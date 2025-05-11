package eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.innerEvents.InnerDomainEventRequestDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.innerEvents.InnerDomainEventsProducerService;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.chat.messaging.HotelInnerEventPublisher;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.InnerHotelEvent;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.AddressValueObject;
import org.modelmapper.ModelMapper;

/**
 * All update Facade entry class
 */
public class HotelAggregateBuilder
{
    private HotelRootDomainEntity hotelDomainEntity;
    private InnerDomainEventsProducerService<InnerHotelEvent> innerDomainEventsProducerService;
    private ModelMapper modelMapper;
    private HotelInnerEventPublisher hotelInnerEventPublisher;

    void setHotelDomainAction(HotelDomainEntityId hotelId){

        AddressValueObject addressValueObject = AddressValueObject.from("street", "city", "nr");

        InnerDomainEventRequestDTO innerEvent = new InnerDomainEventRequestDTO(
                hotelId,
                InnerHotelEvent.EVENT_ADDRESS_UPDATED,
                addressValueObject.serialize()
        );

        innerDomainEventsProducerService.publishInnerEvent(innerEvent);
    }

    void onInnerEvent(InnerDomainEventRequestDTO innerEvent){

        hotelInnerEventPublisher.publishInnerEvent(innerEvent);
    }

    /**
     * Entry Point to all Entity setters!!!
     * muss enter hotelEntityId as parameter
     * @return
     */
//    public static HotelRootDomainEntity.HotelRootDomainEntityBuilder getEntityBuilder(HotelDomainEntityId hotelEntityId){
//        return HotelRootDomainEntity.builder().domainEntityId(hotelEntityId);
//    }
}
