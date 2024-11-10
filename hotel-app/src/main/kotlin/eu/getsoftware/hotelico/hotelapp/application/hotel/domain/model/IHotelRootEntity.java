package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.IHotelActivity;

import java.util.Collection;
import java.util.Set;

public interface IHotelRootEntity {

    //eugen: mappedBy entity!
    Set<? extends IHotelWallPost> hotelWallPosts();

    //eugen: mappedBy entity!
    Set<? extends IHotelActivity> hotelActivities();

    public HotelDomainEntityId getDomainEntityId();

    public Address address = null;
    
    public String getPlainFilePath(final int upperOrderId); 

    public void setMediaUploaded(boolean mediaUploaded);

    boolean isVirtual();

    String getCurrentHotelAccessCode();

    Collection<CustomerDomainEntityId> getStaffIdList();

    double getLatitude();

    double getLongitude();

    String getGuestPushIds();

    Collection<CustomerDomainEntityId> getStaffList();
}
