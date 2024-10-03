package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;

import java.util.Date;

public interface ICustomerHotelCheckin {
    
    void setCustomer(ICustomerRootEntity customerEntity);

    HotelRootEntity getHotel();

    ICustomerRootEntity getCustomer();
    
    boolean isStaffCheckin();

    void setStaffCheckin(boolean staffCheckin);

    void setHotel(IHotelRootEntity category);

    Date getValidFrom();

    Date getValidTo();

    void setValidFrom(Date validFrom);

    void setValidTo(Date validTo);

    boolean isActive();

    void setActive(boolean active);

    int hashCode();

    boolean isFullCheckin();

    void setFullCheckin(boolean fullCheckin);

}
