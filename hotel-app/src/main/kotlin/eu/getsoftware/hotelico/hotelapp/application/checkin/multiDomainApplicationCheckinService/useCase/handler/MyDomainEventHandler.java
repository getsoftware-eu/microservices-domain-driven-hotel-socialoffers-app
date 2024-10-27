package eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainApplicationCheckinService.useCase.handler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyDomainEventHandler {

    /**
    @EventListener //- внутри Spring-Container CustomEvent! (4tek)
    fun(CustomEvent event){
        
    }
    **/

    // Указываем слушатель для очереди
    @RabbitListener(queues = "checkin.checkin.created.event")
    public void onMyDomainEvent(DomainMessage<CheckinEvent> eventMessage) {
        
        var eventPayload = eventMessage.getPayload();
        
        // Handler code here.
        if ("SUCCESS".equals(eventPayload.getCheckinStatus())) {
            // Например, уведомление пользователя, что регистрация прошла успешно
            System.out.println("Регистрация успешна!");
        } else {
            System.out.println("Регистрация не удалась.");
        }
    }
}
