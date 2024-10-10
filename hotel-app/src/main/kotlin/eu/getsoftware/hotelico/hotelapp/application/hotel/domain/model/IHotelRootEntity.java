package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.hotel.model.HotelWallPost;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.persistence.hotel.checkin.model.HotelActivity;

import java.util.Collection;
import java.util.Set;

public interface IHotelRootEntity {

    //eugen: mappedBy entity!
    Set<HotelWallPost> hotelWallPosts();

    //eugen: mappedBy entity!
    Set<HotelActivity> hotelActivities();

    public long getId();

    public String getPlainFilePath(final int upperOrderId); 

    public void setMediaUploaded(boolean mediaUploaded);

    boolean isVirtual();

    String getCurrentHotelAccessCode();

    Collection<CustomerDTO> getStaffList();
}
