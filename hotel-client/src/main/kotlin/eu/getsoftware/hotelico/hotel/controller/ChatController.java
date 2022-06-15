package eu.getsoftware.hotelico.hotel.controller;

import eu.getsoftware.hotelico.hotel.dto.ChatMessageDTO;
import eu.getsoftware.hotelico.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotel.dto.ChatMessageDTO;
import eu.getsoftware.hotelico.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotel.service.ChatService;
import eu.getsoftware.hotelico.hotel.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/chat")
public class ChatController extends BasicController
{

  private Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  private ChatService chatService;
  
  @RequestMapping(method = RequestMethod.GET)
  public String viewApplication() {
    return "chat";
  }

  @MessageMapping("/chat")
  //who should recieve result of this method
//  @SendTo("/chattopic/message") //Send to all
  public ChatMessageDTO sendMessage(ChatMessageDTO chatMessageDto) {
    logger.info("Message sent");

    ChatMessageDTO savedMessage = chatService.addChatMessage(chatMessageDto);
    
    return savedMessage;
  }

  @RequestMapping(value = "/messages/sender/{customerId}/receiver/{receiverId}", method = RequestMethod.GET)
  public @ResponseBody
  List<ChatMessageDTO> getMessagesByCustomerlId(@PathVariable long customerId, @PathVariable int receiverId) {
    return chatService.getMessagesByCustomerId(customerId, receiverId);
  }

  @RequestMapping(value = "/notHotelChatPartners/customer/{customerId}/city/{city}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  Set<CustomerDTO> getNotHotelChatPartners(@PathVariable long customerId, @PathVariable String city, @PathVariable long hotelId) {
    return chatService.getNotHotelChatPartners(customerId, city, hotelId);
  }
  
  @RequestMapping(value = "/allContactChatPartners/customer/{customerId}/city/{city}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  Set<CustomerDTO> getAllContactChatPartners(@PathVariable long customerId, @PathVariable String city, @PathVariable long hotelId) {
    return chatService.getAllContactChatPartners(customerId, city, hotelId);
  }

  @RequestMapping(value = "/allNotChatPartners/customer/{customerId}/city/{city}/hotel/{hotelId}/page/{pageNumber}", method = RequestMethod.GET)
  public @ResponseBody
  Set<CustomerDTO> getAllNotChatPartners(@PathVariable long customerId, @PathVariable String city, @PathVariable long hotelId, @PathVariable int pageNumber) {
   
    if("-1".equalsIgnoreCase(city))
    {
      city = null;
    }
   
    return chatService.getAllNotChatPartners(customerId, city, hotelId, pageNumber);
  }

  @RequestMapping(value = "/markReadMessage/receiverId/{customerId}/messageIds/{messageIds}", method = RequestMethod.GET)
  public @ResponseBody
  List<ChatMessageDTO> markMessageRead(@PathVariable long customerId, @PathVariable String messageIds) {
     chatService.markMessageRead(customerId, messageIds);
    
    return new ArrayList<>();
  } 
  
  @RequestMapping(value = "/markReadChat/receiverId/{customerId}/senderId/{senderId}/maxSeenChatMessageId/{maxSeenChatMessageId}", method = RequestMethod.GET)
  public @ResponseBody
  List<ChatMessageDTO> markChatRead(@PathVariable long customerId, @PathVariable int senderId, @PathVariable long maxSeenChatMessageId) {
     chatService.markChatRead(customerId, senderId, maxSeenChatMessageId);
    
    return null;
  }


  @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.POST)
//  @SendTo("/chattopic/message")
  public @ResponseBody ChatMessageDTO addMessagesByMessageId(@PathVariable long messageId, @RequestBody ChatMessageDTO dto) {
    return chatService.addUpdateChatMessage(dto);
  }
}
