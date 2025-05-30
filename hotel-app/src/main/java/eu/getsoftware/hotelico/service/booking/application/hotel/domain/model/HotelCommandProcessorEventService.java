package eu.getsoftware.hotelico.service.booking.application.hotel.domain.model;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.CheckinDeletedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.common.domain.ids.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.gateways.HotelGatewayService;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HotelCommandProcessorEventService {

    private final HotelGatewayService hotelGatewayService;

    protected void onCheckinCreatedEvent(CheckinUpdatedEventPayload payload) {
        HotelDomainEntityId domainId = payload.getHotelId();

        HotelRootDomainEntity hotelDomain = hotelGatewayService.findOrThrow(domainId);

        hotelDomain.addHotelGuest(payload.getCustomerId());

        return;
    }

    protected void onCheckinUpdatedEvent(CheckinUpdatedEventPayload payload) {
        HotelDomainEntityId domainId = payload.getHotelId();

        HotelRootDomainEntity hotelDomain = hotelGatewayService.findOrThrow(domainId);


        //TODO update checkin
        
        return;
    }

    protected void onCheckinDeletedEvent(CheckinDeletedEventPayload payload) {
        HotelDomainEntityId domainId = payload.getHotelId();

        HotelRootDomainEntity hotelDomain = hotelGatewayService.findOrThrow(domainId);

        hotelDomain.removeHotelGuest(payload.getCustomerId());

        return;
    }
}
