package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model;

public interface IHotelActivity {
    
    long getId();

    String getPlainFilePath(final int upperOrderId);

    void setMediaUploaded(boolean mediaUploaded);

    ICustomerRootEntity getHotelRootEntity();
}
