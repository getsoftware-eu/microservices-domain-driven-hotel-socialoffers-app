package eu.getsoftware.hotelico.hotel.infrastructure.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.getsoftware.hotelico.hotel.infrastructure.aspects.NotifyClients;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelActivityDto;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.service.HotelService;

@Controller
@RequestMapping("/activity")
public class ActivityController extends BasicController
{

  private Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  private HotelService hotelService;
	
  @RequestMapping(method = RequestMethod.GET)
  public String viewApplication() {
    return "activity";
  }

  @MessageMapping("/activity")
  //who should recieve result of this method
  @SendTo("/activitytopic/message")
  public HotelActivityDto sendActivity(HotelActivityDto activityDto) {
    logger.info("Activity sent");

    HotelActivityDto savedActivity = hotelService.addUpdateHotelActivity(activityDto.getSenderId(), activityDto);
    
    return activityDto;
  }

  @RequestMapping(value = "/activities/customer/{customerId}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  List<HotelActivityDto> getActivitiesByHotelId(@PathVariable long customerId, @PathVariable long hotelId) {
    //TODO Eugen: socket ConnectException: Connection timed out: connect
    return hotelService.getHotelActivitiesByHotelId(customerId, hotelId);
  }
  
  @RequestMapping(value = "/action/{action}/customer/{customerId}/activityId/{activityId}", method = RequestMethod.GET)
  public @ResponseBody
  HotelActivityDto addActivityAction(@PathVariable String action, @PathVariable long customerId, @PathVariable long activityId) {
    return hotelService.addActivityAction(customerId, activityId, action);
  }
  
  @RequestMapping(value = "/activities/sender/{senderId}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  List<HotelActivityDto> getActivitiesByCreatorAndHotelId(@PathVariable long senderId, @PathVariable long hotelId) {
    return hotelService.getHotelActivitiesBySenderAndHotelId(senderId, hotelId);
  }  
  
  @RequestMapping(value = "/activities/customer/{customerId}/activityId/{activityId}", method = RequestMethod.GET)
  public @ResponseBody
  HotelActivityDto getActivityById(@PathVariable long customerId, @PathVariable long activityId) {
    return hotelService.getHotelActivityById(customerId, activityId);
  }
//  
//  @RequestMapping(value = "/activities/hotel/{id}", method = RequestMethod.GET)
//  public List<HotelActivityDto> getHotnelActivitiesByHotelId(@PathVariable int id) {
//    List<HotelActivityDto> out = hotelService.getHotelActivitiesByHotelId(id);
//    return out;
//  }

//  @NotifyClients
//  @RequestMapping(value = "/activity/{id}", method = RequestMethod.PUT)
//  public HotelActivityDto updateHotelActivity(@PathVariable int id, @RequestBody HotelActivityDto hotelActivityDto) {
//    hotelActivityDto.setId(id);
//    HotelActivityDto out = hotelService.updateHotelActivity(hotelActivityDto);
//    return out;
//  }
  
  @NotifyClients
  @RequestMapping(value = "/customer/{customerId}/activity/{activityId}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public @ResponseBody ResponseDTO deleteHotelActivity(@PathVariable long customerId, @PathVariable long activityId) {
    
    return hotelService.deleteHotelActivity(customerId, activityId);
  }

  @RequestMapping(value = "/customer/{customerId}/activity/{activityId}", method = RequestMethod.POST)//, headers ="Accept:*/*")
  public @ResponseBody 
  HotelActivityDto addUpdateActivity(@PathVariable long customerId, @PathVariable long activityId, @RequestBody HotelActivityDto activityDto) {

//    activityDto.setInitId(activityId);
    HotelActivityDto out = hotelService.addUpdateHotelActivity(customerId, activityDto);
    return out;
  }

//  @NotifyClients
//  @SendTo("/activitytopic/message")
//  @RequestMapping(value = "/customer/activity", method = RequestMethod.PUT, headers ="Accept:*/*")
//  public @ResponseBody HotelActivityDto addActivity(@RequestBody HotelActivityDto activityDto) {
////    activityDto.setInitId(activityId);
//    HotelActivityDto out = hotelService.addUpdateHotelActivity(activityDto.getSenderId(), activityDto);
//    return out;
//  }

}
