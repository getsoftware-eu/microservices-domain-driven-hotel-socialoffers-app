package eu.getsoftware.hotelico.clients.api.application.infrastructure.domainEvents.innerEvents;

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
