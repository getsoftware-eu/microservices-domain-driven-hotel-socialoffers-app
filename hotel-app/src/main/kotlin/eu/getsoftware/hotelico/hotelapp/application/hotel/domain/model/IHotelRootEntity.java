package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.checkin.model.HotelDbActivity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelWallPost;

import java.util.Collection;
import java.util.Set;

public interface IHotelRootEntity {

    //eugen: mappedBy entity!
    Set<HotelWallPost> hotelWallPosts();

    //eugen: mappedBy entity!
    Set<HotelDbActivity> hotelActivities();

    public long getId();

    public String getPlainFilePath(final int upperOrderId); 

    public void setMediaUploaded(boolean mediaUploaded);

    boolean isVirtual();

    String getCurrentHotelAccessCode();

    Collection<CustomerDTO> getStaffList();

    double getLatitude();

    double getLongitude();

    String getGuestPushIds();
}
