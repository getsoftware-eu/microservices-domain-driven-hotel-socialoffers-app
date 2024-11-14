package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.adapter.in.web.controller.BasicController;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wall")
public class WallController  extends BasicController
{

  private Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  private IHotelService hotelService;
  
  @RequestMapping(method = RequestMethod.GET)
  public String viewApplication() {
    return "wall";
  }

  @MessageMapping("/wall")
  //who should recieve result of this method
//  @SendTo("/walltopic/message")
  public WallPostDTO sendMessage(WallPostDTO wallPostDto) throws Throwable {
    logger.info("Message sent");

    WallPostDTO savedMessage = hotelService.addUpdateWallPost(wallPostDto);
 
    return savedMessage;
  }

  @RequestMapping(value = "/messages/customer/{requesterId}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  List<WallPostDTO> getMessagesByHotelId(@PathVariable HotelDomainEntityId hotelId, @PathVariable long requesterId) {
    return hotelService.getWallPostsByHotelId(hotelId);
  }
  
  @RequestMapping(value = "/customers/{requesterId}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  List<CustomerDTO> getWallParticipantsByHotelId(@PathVariable CustomerDomainEntityId requesterId, @PathVariable HotelDomainEntityId hotelId) {
    return hotelService.getWallPostParticipantsByHotelId(requesterId, hotelId);
  }
  
  @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.POST)
  @SendTo("/walltopic/message")
  public @ResponseBody
  WallPostDTO addMessagesByMessageId(@PathVariable long messageId, @RequestBody WallPostDTO dto) throws Throwable {
    return hotelService.addUpdateWallPost(dto);
  }
//  @RequestMapping(value = "/messages/{messageId}", method = HttpMethod.PUT)
//  public @ResponseBody
//  WallPostDto putMessagesByMessageId(@RequestBody WallPostDto dto) {
//    return null;
//  }
}
