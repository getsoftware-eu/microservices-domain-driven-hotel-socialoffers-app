package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents;

public interface InnerDomainEvent {
    
    String getValue();

    String getPushUrl();

    String getPushTitle();

    String getPushIcon();

    Class getEntityClass();

    String getEntityString();

//    HotelEvent parseByValue(String value);
    
    InnerDomainEvent getEventCheckin();
    InnerDomainEvent getEventCheckout();
}
