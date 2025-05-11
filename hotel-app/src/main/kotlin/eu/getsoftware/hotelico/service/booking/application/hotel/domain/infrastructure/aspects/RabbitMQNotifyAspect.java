//package eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.aspects;
//
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//
///**
// * notify users about
// */
//
//@Aspect
//public class RabbitMQNotifyAspect {
//
//    @Autowired
//    private ProduceRabbitmqMessageService produceRabbitmqMessageService;
//
//    private static final String WEBSOCKET_TOPIC = "/topic/notify";
//    
//    @Pointcut("@annotation(eu.getsoftware.hotelico.hotel.aspects.NotifyClients)")
//    public void notifyPointcut() {}
//
//    @Pointcut("execution(* de.hotelico.controller.**.*(..))")
//    public void methodPointcut() {}
//
//    //TODO Eugen:   @NotifyClients  Annotation!!!!!
//
//    @After("methodPointcut() && notifyPointcut()") 
//    public void notifyClients() throws Throwable {
//        webSocketService.produceSimpWebsocketMessage(WEBSOCKET_TOPIC, new Date());
//    }
//
//}
