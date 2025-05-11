package eu.getsoftware.hotelico.service.booking.application.customer.domain.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;

import java.util.Collection;

public interface IHotelActivity {

    long getId();

    String getPlainFilePath(final int upperOrderId);

    void setMediaUploaded(boolean mediaUploaded);

    HotelDomainEntityId getHotelDomainId();

    String senderId();

//    Collection<CustomerDomainEntityId> getLikedCustomerDomainEntityIds();

//    Collection<CustomerDomainEntityId> getSubscribeCustomerDomainEntityIds();

    void setLikedCustomerDomainEntityIds(Collection<CustomerDomainEntityId> likedBy);

    void setSubscribeCustomerDomainEntityIds(Collection<CustomerDomainEntityId> subscribeBy);
}
