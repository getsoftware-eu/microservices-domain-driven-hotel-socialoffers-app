package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.`in`.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO
import eu.getsoftware.hotelico.clients.common.adapter.`in`.web.controller.BasicController
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.port.out.ChatService
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.ArrayList

@Controller
@RequestMapping("/chat")
public class ChatController(
  private val chatService: ChatService,
  private val objectMapper: ObjectMapper
) : BasicController()
{
  companion object { //static
    //private
    private val logger: Logger = LoggerFactory.getLogger(this::class.java);
  }
  
  @GetMapping
  fun viewApplication(): String {
    return "chat";
  }

  @ApiOperation(value = "basic send chat method", produces = "application/json")
  @MessageMapping("/chat")
  //who should recieve result of this method
//  @SendTo("/chattopic/message") //Send to all
  fun sendMessage(chatMessageDto: ChatMsgDTO): ChatMsgDTO {
    //logger.info("Message sent");
    logger.info (
      objectMapper.writeValueAsString(
        mapOf(
            "event" to "sendMessage",
            "ChatMsgDTO" to objectMapper.writeValueAsString(chatMessageDto)
        )
      )
    )

    return chatService.addChatMessage(chatMessageDto)
  }

  @GetMapping(value = "/messages/sender/{customerId}/receiver/{receiverId}")
  fun @ResponseBody
  List<ChatMsgDTO> getMessagesByCustomerlId(@PathVariable long customerId, @PathVariable int receiverId) {
    return chatService.getMessagesByCustomerId(customerId, receiverId);
  }

  @GetMapping(value = "/notHotelChatPartners/customer/{customerId}/city/{city}/hotel/{hotelId}")
  fun @ResponseBody
  Set<CustomerDTO> getNotHotelChatPartners(@PathVariable long customerId, @PathVariable String city, @PathVariable long hotelId) {
    return chatService.getNotHotelChatPartners(customerId, city, hotelId);
  }
  
  @GetMapping(value = "/allContactChatPartners/customer/{customerId}/city/{city}/hotel/{hotelId}")
  fun @ResponseBody
  Set<CustomerDTO> getAllContactChatPartners(@PathVariable long customerId, @PathVariable String city, @PathVariable long hotelId) {
    return chatService.getAllContactChatPartners(customerId, city, hotelId);
  }

  @GetMapping(value = "/allNotChatPartners/customer/{customerId}/city/{city}/hotel/{hotelId}/page/{pageNumber}")
  fun @ResponseBody
  Set<CustomerDTO> getAllNotChatPartners(@PathVariable long customerId, @PathVariable String city, @PathVariable long hotelId, @PathVariable int pageNumber) {
   
    if("-1".equalsIgnoreCase(city))
    {
      city = null;
    }
   
    return chatService.getAllNotChatPartners(customerId, city, hotelId, pageNumber);
  }

  @GetMapping(value = "/markReadMessage/receiverId/{customerId}/messageIds/{messageIds}")
  fun @ResponseBody
  List<ChatMsgDTO> markMessageRead(@PathVariable long customerId, @PathVariable String messageIds) {
     chatService.markMessageRead(customerId, messageIds);
    
    return new ArrayList<>();
  } 
  
  @GetMapping(value = "/markReadChat/receiverId/{customerId}/senderId/{senderId}/maxSeenChatMessageId/{maxSeenChatMessageId}")
  fun @ResponseBody
  List<ChatMsgDTO> markChatRead(@PathVariable long customerId, @PathVariable int senderId, @PathVariable long maxSeenChatMessageId) {
     chatService.markChatRead(customerId, senderId, maxSeenChatMessageId);
    
    return null;
  }


  @GetMapping(value = "/messages/{messageId}")
//  @SendTo("/chattopic/message")
  fun @ResponseBody ChatMsgDTO addMessagesByMessageId(@PathVariable long messageId, @RequestBody ChatMsgDTO dto) {
    return chatService.addUpdateChatMessage(dto);
  }
}
