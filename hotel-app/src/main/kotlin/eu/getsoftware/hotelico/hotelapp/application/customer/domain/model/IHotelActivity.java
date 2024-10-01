package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model;

import eu.getsoftware.hotelico.clients.api.clients.domain.customer.ICustomerRootEntity;

public interface IHotelActivity {
    
    long getId();

    String getPlainFilePath(final int upperOrderId);

    void setMediaUploaded(boolean mediaUploaded);

    ICustomerRootEntity getHotelRootEntity();
}
