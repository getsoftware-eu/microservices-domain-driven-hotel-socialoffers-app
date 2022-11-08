package eu.getsoftware.hotelico.hotel.infrastructure.service.impl;

import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import eu.getsoftware.hotelico.hotel.infrastructure.service.CacheService;

/**
 *  * http://www.theotherian.com/2014/03/spring-boot-websockets-stomp-chat.html?m=1
 */
public class ActiveCustomerPinger
{
  private SimpMessagingTemplate template;
  private CacheService cacheService;

  public ActiveCustomerPinger(SimpMessagingTemplate template, CacheService cacheService) {
    this.template = template;
    this.cacheService = cacheService;
  }
  
  @Scheduled(fixedDelay = 2000)
  public void pingUsers() {
    List<Long> activeUsers = cacheService.getOnlineCustomerIds();
    template.convertAndSend("/topic/active", activeUsers);
  }

}
