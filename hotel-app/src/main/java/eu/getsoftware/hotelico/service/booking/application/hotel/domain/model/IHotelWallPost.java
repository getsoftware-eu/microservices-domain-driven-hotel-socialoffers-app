package eu.getsoftware.hotelico.service.booking.application.hotel.domain.model;

import eu.getsoftware.hotelico.clients.common.domain.ids.WallPostDomainEntityId;

public interface IHotelWallPost {
    
    boolean isActive();

    String getMessage();

    String getSpecialWallContent();

    WallPostDomainEntityId getDomainEntityId();
}
