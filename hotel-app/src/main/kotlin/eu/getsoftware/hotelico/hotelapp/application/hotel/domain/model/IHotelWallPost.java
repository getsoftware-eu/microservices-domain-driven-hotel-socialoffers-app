package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.WallPostDomainEntityId;

public interface IHotelWallPost {
    
    boolean isActive();

    String getMessage();

    String getSpecialWallContent();

    WallPostDomainEntityId getDomainEntityId();
}
