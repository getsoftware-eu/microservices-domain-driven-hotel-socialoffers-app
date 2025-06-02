//package eu.getsoftware.hotelico.service.booking.application.checkin.multiDomainOrchestratorCheckinService.useCase.handler;
//
//import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainLayerPayload.CheckinEvent;
//import eu.getsoftware.hotelico.clients.api.clients.infrastructure.amqpConsumeNotification.domainMessage.DomainMessage;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyDomainEventHandler {
//
//    /**
//    @EventListener //- внутри Spring-Container CustomEvent! (4tek)
//    fun(CustomEvent event){
//        
//    }
//    **/
//
//    // Указываем слушатель для очереди
//    @RabbitListener(queues = "checkin.checkin.created.event")
//    public void onMyDomainEvent(DomainMessage<CheckinEvent> eventMessage) {
//        
//        var eventPayload = eventMessage.getPayload();
//        
//        // Handler code here.
//        if ("SUCCESS".equals(eventPayload.getCheckinStatus())) {
//            // Например, уведомление пользователя, что регистрация прошла успешно
//            System.out.println("Регистрация успешна!");
//        } else {
//            System.out.println("Регистрация не удалась.");
//        }
//    }
//}
