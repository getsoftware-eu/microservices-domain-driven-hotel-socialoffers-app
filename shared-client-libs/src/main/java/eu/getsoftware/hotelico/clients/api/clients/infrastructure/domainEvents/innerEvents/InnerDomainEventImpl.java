package eu.getsoftware.hotelico.clients.api.clients.infrastructure.domainEvents.innerEvents;

public enum InnerDomainEventImpl implements InnerDomainEvent {
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
    public InnerDomainEvent getEventCheckin() {
        return null;
    }

    @Override
    public InnerDomainEvent getEventCheckout() {
        return null;
    }
}
