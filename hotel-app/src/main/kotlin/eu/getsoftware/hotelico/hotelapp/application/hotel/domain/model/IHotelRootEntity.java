package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelWallPost;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotelCustomer.model.HotelActivity;

import java.util.Set;

public interface IHotelRootEntity {

    //eugen: mappedBy entity!
    private Set<HotelWallPost> hotelWallPosts;

    //eugen: mappedBy entity!
    private Set<HotelActivity> hotelActivities;

    public long getId();

    public String getPlainFilePath(final int upperOrderId); 

    public void setMediaUploaded(boolean mediaUploaded);

}
