package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.innerDomainService.domainEvents;

import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;

public enum DomainEvent implements IHotelEvent {
    TEST;

    @Override
    public String getValue() {
        return "";
    }

    @Override
    public String getPushUrl() {
        return "";
    }

    @Override
    public String getPushTitle() {
        return "";
    }

    @Override
    public String getPushIcon() {
        return "";
    }

    @Override
    public Class getEntityClass() {
        return null;
    }

    @Override
    public String getEntityString() {
        return "";
    }

    @Override
    public IHotelEvent getEventCheckin() {
        return null;
    }

    @Override
    public IHotelEvent getEventCheckout() {
        return null;
    }
}
