package eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.useCase.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyDomainEventHandler {
    @EventListener
    public void onMyDomainEvent(MyDomainEvent event) {
        // Handler code here.
    }
}
