package chat.adapter.`in`.web.controller;

import chat.application.port.out.ChatDTOService
import chat.application.port.out.ChatService
import com.fasterxml.jackson.databind.ObjectMapper
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO
import eu.getsoftware.hotelico.clients.common.adapter.`in`.web.controller.BasicController
import io.swagger.annotations.ApiOperation
import mu.KotlinLogging
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@Controller
@RequestMapping("/chat")
class ChatController(
  private val chatService: ChatService,
  private val chatDTOService: ChatDTOService,
  private val objectMapper: ObjectMapper
) : BasicController()
{

  @GetMapping
  fun viewApplication(): String {
    return "chat";
  }

  @ApiOperation(value = "basic send chat method", produces = "application/json")
  @MessageMapping("/chat")
  //who should recieve result of this method
//  @SendTo("/chattopic/message") //Send to all
  fun sendMessage(chatMessageDto: ChatMsgDTO): ChatMsgDTO {
    log.info { //lambda
        mapOf(
          "event" to "sendMessage",
          "ChatMsgDTO" to chatMessageDto
        )
    }
    
    return chatService.addChatMessage(chatMessageDto)
  }

  @GetMapping( "/messages/sender/{customerId}/receiver/{receiverId}")
  @ResponseBody
  fun getMessagesByCustomerlId(@PathVariable customerId: Long, @PathVariable receiverId: Int):List<ChatMsgDTO> {
    return chatService.getMessagesByCustomerId(customerId, receiverId);
  }

  @GetMapping("/notHotelChatPartners/customer/{customerId}/city/{city}/hotel/{hotelId}")
  @ResponseBody
  fun getNotHotelChatPartners(@PathVariable customerId: Long, @PathVariable city: String, @PathVariable hotelId: Long):Set<CustomerDTO> {
    return chatService.getNotHotelChatPartners(customerId, city, hotelId);
  }
  
  @GetMapping("/allContactChatPartners/customer/{customerId}/city/{city}/hotel/{hotelId}")
  @ResponseBody
  fun getAllContactChatPartners(@PathVariable customerId: Long, @PathVariable city: String, @PathVariable hotelId: Long): Set<CustomerDTO> {
    return chatService.getAllContactChatPartners(customerId, city, hotelId);
  }

  @GetMapping("/allNotChatPartners/customer/{customerId}/city/{city}/hotel/{hotelId}/page/{pageNumber}")
  @ResponseBody
  fun getAllNotChatPartners(@PathVariable customerId: Long, @PathVariable city: String, @PathVariable hotelId: Long, @PathVariable pageNumber: Int): Set<CustomerDTO> {
   
    var customCity: String? = city
    
    if("-1".equals(city, true))
    {
      customCity = null;
    }
   
    return chatService.getAllNotChatPartners(customerId, customCity, hotelId, pageNumber);
  }

  @GetMapping("/markReadMessage/receiverId/{customerId}/messageIds/{messageIds}")
  @ResponseBody
  fun markMessageRead(@PathVariable customerId: Long, @PathVariable messageIds: String): List<ChatMsgDTO> {
     chatService.markMessageRead(customerId, messageIds);
    
    return listOf();
  } 
  
  @GetMapping("/markReadChat/receiverId/{customerId}/senderId/{senderId}/maxSeenChatMessageId/{maxSeenChatMessageId}")
  @ResponseBody
  fun markChatRead(@PathVariable customerId: Long, @PathVariable senderId: Int, @PathVariable maxSeenChatMessageId: Long): List<ChatMsgDTO>  {
     chatService.markChatRead(customerId, senderId, maxSeenChatMessageId);
    return listOf();
  }
  
  @GetMapping("/messages/{messageId}")
  // @SendTo("/chattopic/message")
  @ResponseBody 
  fun addMessagesByMessageId(@PathVariable messageId: Long, @RequestBody dto: ChatMsgDTO): ChatMsgDTO  {
    return chatService.addUpdateChatMessage(dto);
  }
}
