package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.CheckinDeletedEventPayload;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.CheckinUpdatedEventPayload;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.gateways.HotelGatewayService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.customDomainModelImpl.HotelRootDomainEntity;
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
