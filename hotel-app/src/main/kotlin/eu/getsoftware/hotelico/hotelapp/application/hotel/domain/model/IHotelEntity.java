package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import java.util.Set;

public interface IHotelEntity {
    //eugen: mappedBy entity!
    Set<? extends IHotelWallPost> getHotelWallPosts();

}
