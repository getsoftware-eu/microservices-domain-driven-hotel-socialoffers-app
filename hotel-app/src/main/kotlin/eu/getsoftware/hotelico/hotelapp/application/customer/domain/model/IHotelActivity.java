package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.HotelDomainEntity;

import java.util.Collection;

public interface IHotelActivity {
    
    long getId();

    String getPlainFilePath(final int upperOrderId);

    void setMediaUploaded(boolean mediaUploaded);

    <T extends HotelDomainEntity> T getHotel();

    String senderId();

    Collection<CustomerDomainEntityId> getLikedCustomerDomainEntityIds();

    Collection<CustomerDomainEntityId> getSubscribeCustomerDomainEntityIds();

    void setLikedCustomerDomainEntityIds(Collection<CustomerDomainEntityId> likedBy);

    void setSubscribeCustomerDomainEntityIds(Collection<CustomerDomainEntityId> subscribeBy);
}
