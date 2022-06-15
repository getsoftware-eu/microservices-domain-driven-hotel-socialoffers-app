package eu.getsoftware.hotelico.hotel.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Date;

/**
 * notify users about
 */

@Aspect
public class NotifyAspect {

    @Autowired
    private SimpMessagingTemplate template;

    private static final String WEBSOCKET_TOPIC = "/topic/notify";
    
    @Pointcut("@annotation(eu.getsoftware.hotelico.hotel.aspects.NotifyClients)")
    public void notifyPointcut() {}

    @Pointcut("execution(* de.hotelico.controller.**.*(..))")
    public void methodPointcut() {}

    //TODO Eugen:   @NotifyClients  Annotation!!!!!

    @After("methodPointcut() && notifyPointcut()") 
    public void notifyClients() throws Throwable {
        template.convertAndSend(WEBSOCKET_TOPIC, new Date());
    }

}
