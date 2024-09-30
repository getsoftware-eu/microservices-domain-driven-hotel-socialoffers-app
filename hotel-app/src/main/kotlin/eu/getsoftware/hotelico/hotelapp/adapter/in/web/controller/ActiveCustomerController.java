package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller;//package de.hotelico.controller;
//
//import jakarta.inject.Inject;
//
//import org.springframework.messaging.Message;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.stereotype.Controller;
//
//import eu.getsoftware.hotelico.model.Customer;
//import CacheService;
//
///**
// * <br/>
// * Created by e.fanshil
// * At 17.11.2015 14:45
// * http://www.theotherian.com/2014/03/spring-boot-websockets-stomp-chat.html?m=1
// */
//@Controller
//public class ActiveCustomerController
//{
//	private CacheService cacheService;
//	
//	@Inject
//	public ActiveCustomerController(CacheService cacheService) {
//		this.cacheService = cacheService;
//	}
//	
////	@MessageMapping("/activeUsers")
////	public void activeUsers(Message<Object> message) {
////		Principal user = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
////		activeUserService.mark(user.getName());
////	}
//	
//	@MessageMapping("/activeUsers")
//	public void activeUsers(Message<Object> message) {
//		Customer customer = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Customer.class);
//		
//		if(customer!=null)
//		{
//			cacheService.checkCustomerOnline(customer.getId());
//		}
//	}
//}
