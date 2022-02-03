package eu.getsoftware.hotelico.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This aspect has two pointcuts, the notifyPointcut() looks for all methods annotated with our annotation and the methodPointcut() looks for all methods in the controllers-package. 
 * We then weave our code into the controller after the given pointcuts. We have to wait until all changes are persisted before we can notify the clients. 
 * When such a controller method is executed, it will send the current timestamp to a websocket called /topic/notify. We will also use this topic in the client-side code to subscribe on.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotifyClients {

}
