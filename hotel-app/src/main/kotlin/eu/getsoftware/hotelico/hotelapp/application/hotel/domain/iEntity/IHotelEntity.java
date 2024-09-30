package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.iEntity;

import java.util.Set;

public interface IHotelEntity {
    //eugen: mappedBy entity!
    Set<IHotelWallpost> getHotelWallPosts();

}
