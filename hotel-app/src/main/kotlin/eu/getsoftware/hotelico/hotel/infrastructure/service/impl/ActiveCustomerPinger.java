package eu.getsoftware.hotelico.hotel.infrastructure.service.impl;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import eu.getsoftware.hotelico.hotel.infrastructure.service.LastMessagesService;

/**
 *  * http://www.theotherian.com/2014/03/spring-boot-websockets-stomp-chat.html?m=1
 */
public class ActiveCustomerPinger
{
  private SimpMessagingTemplate template;
  private LastMessagesService lastMessagesService;

  public ActiveCustomerPinger(SimpMessagingTemplate template, LastMessagesService lastMessagesService) {
    this.template = template;
    this.lastMessagesService = lastMessagesService;
  }
  
  @Scheduled(fixedDelay = 2000)
  public void pingUsers() {
    List<Long> activeUsers = lastMessagesService.getOnlineCustomerIds();
    template.convertAndSend("/topic/active", activeUsers);
  }

}
