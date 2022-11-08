package eu.getsoftware.hotelico.main.hotel.config;


import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import eu.getsoftware.hotelico.hotel.infrastructure.service.LastMessagesService;
import eu.getsoftware.hotelico.hotel.infrastructure.service.impl.ActiveCustomerPinger;

/**
 * Override the scheduling configuration so that we can schedule our own scheduled bean and
 * so that Spring's STOMP scheduling can continue to work
 * http://www.theotherian.com/2014/03/spring-boot-websockets-stomp-chat.html?m=1
 *  
 * 
 */
@Configuration
@EnableScheduling
public class HeartBeatConfigurer implements SchedulingConfigurer
{
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		return new ThreadPoolTaskScheduler();
	}
	
	/**
	 * This is setting up a scheduled bean which will see which users are active
	 * http://www.theotherian.com/2014/03/spring-boot-websockets-stomp-chat.html?m=1
	 */
	@Bean
	@Inject
	public ActiveCustomerPinger activeUserPinger(SimpMessagingTemplate template, LastMessagesService lastMessagesService) {
		return new ActiveCustomerPinger(template, lastMessagesService);
	}
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setTaskScheduler(taskScheduler());
	}
}
