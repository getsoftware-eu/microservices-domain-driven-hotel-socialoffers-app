package de.hotelico.controller;

import de.hotelico.dto.CustomerDTO;
import de.hotelico.dto.WallPostDto;
import de.hotelico.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/wall")
public class WallController  extends BasicController
{

  private Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  private HotelService hotelService;
  
  @RequestMapping(method = RequestMethod.GET)
  public String viewApplication() {
    return "wall";
  }

  @MessageMapping("/wall")
  //who should recieve result of this method
//  @SendTo("/walltopic/message")
  public WallPostDto sendMessage(WallPostDto wallPostDto) {
    logger.info("Message sent");

    WallPostDto savedMessage = hotelService.addUpdateWallPost(wallPostDto);
 
    return savedMessage;
  }

  @RequestMapping(value = "/messages/customer/{requesterId}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  List<WallPostDto> getMessagesByHotelId(@PathVariable long hotelId, @PathVariable long requesterId) {
    return hotelService.getWallPostsByHotelId(hotelId);
  }
  
  @RequestMapping(value = "/customers/{requesterId}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  List<CustomerDTO> getWallParticipantsByHotelId(@PathVariable long requesterId, @PathVariable long hotelId) {
    return hotelService.getWallPostParticipantsByHotelId(requesterId, hotelId);
  }
  
  @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.POST)
  @SendTo("/walltopic/message")
  public @ResponseBody
  WallPostDto addMessagesByMessageId(@PathVariable long messageId, @RequestBody WallPostDto dto) {
    return hotelService.addUpdateWallPost(dto);
  }
//  @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.PUT)
//  public @ResponseBody
//  WallPostDto putMessagesByMessageId(@RequestBody WallPostDto dto) {
//    return null;
//  }
}
