package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl.microservice;

import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWebSocketService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 *  * http://www.theotherian.com/2014/03/spring-boot-websockets-stomp-chat.html?m=1
 */
@AllArgsConstructor
public class ActiveCustomerPinger
{
  private LastMessagesService lastMessagesService;
  private IWebSocketService webSocketService;

  @Scheduled(fixedDelay = 2000)
  public void pingUsers() {
    List<Long> activeUsers = lastMessagesService.getOnlineCustomerIds();
    
    String webSocketTopic = "/topic/active";
    webSocketService.produceSimpWebsocketMessage(webSocketTopic, activeUsers);
  }

}
