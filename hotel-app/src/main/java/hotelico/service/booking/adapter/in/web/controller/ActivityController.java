package hotelico.service.booking.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.common.adapter.in.web.controller.BasicController;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.ResponseDTO;
import hotelico.service.booking.application.hotel.domain.infrastructure.aspects.NotifyClients;
import hotelico.service.booking.application.hotel.port.out.iPortService.IHotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController extends BasicController
{

  private Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  private IHotelService hotelService;
	
  @RequestMapping(method = RequestMethod.GET)
  public String viewApplication() {
    return "activity";
  }

  @MessageMapping("/activity")
  //who should recieve result of this method
  @SendTo("/activitytopic/message")
  public HotelActivityDTO sendActivity(HotelActivityDTO activityDto) throws Throwable {
    logger.info("Activity sent");

    HotelActivityDTO savedActivity = hotelService.addUpdateHotelActivity(activityDto.getSenderDomainId(), activityDto);
    
    return activityDto;
  }

  @RequestMapping(value = "/activities/customer/{customerId}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  List<HotelActivityDTO> getActivitiesByHotelId(@PathVariable CustomerDomainEntityId customerId, @PathVariable HotelDomainEntityId hotelId) {
    //TODO Eugen: socket ConnectException: Connection timed out: connect
    return hotelService.getHotelActivitiesByHotelId(customerId, hotelId);
  }
  
  @RequestMapping(value = "/action/{action}/customer/{customerId}/activityId/{activityId}", method = RequestMethod.GET)
  public @ResponseBody
  HotelActivityDTO addActivityAction(@PathVariable String action, @PathVariable CustomerDomainEntityId customerId, @PathVariable long activityId) {
    return hotelService.addActivityAction(customerId, activityId, action);
  }
  
  @RequestMapping(value = "/activities/sender/{senderId}/hotel/{hotelId}", method = RequestMethod.GET)
  public @ResponseBody
  List<HotelActivityDTO> getActivitiesByCreatorAndHotelId(@PathVariable CustomerDomainEntityId senderId, @PathVariable HotelDomainEntityId hotelId) {
    return hotelService.getHotelActivitiesBySenderAndHotelId(senderId, hotelId);
  }  
  
  @RequestMapping(value = "/activities/customer/{customerId}/activityId/{activityId}", method = RequestMethod.GET)
  public @ResponseBody
  HotelActivityDTO getActivityById(@PathVariable CustomerDomainEntityId customerId, @PathVariable long activityId) throws Throwable {
    return hotelService.getHotelActivityById(customerId, activityId);
  }
//  
//  @RequestMapping(value = "/activities/hotel/{id}", method = RequestMethod.GET)
//  public List<HotelActivityDto> getHotnelActivitiesByHotelId(@PathVariable int id) {
//    List<HotelActivityDto> out = hotelService.getHotelActivitiesByHotelId(id);
//    return out;
//  }

//  @NotifyClients
//  @RequestMapping(value = "/activity/{id}", method = HttpMethod.PUT)
//  public HotelActivityDto updateHotelActivity(@PathVariable int id, @RequestBody HotelActivityDto hotelActivityDto) {
//    hotelActivityDto.setId(id);
//    HotelActivityDto out = hotelService.updateHotelActivity(hotelActivityDto);
//    return out;
//  }
  
  @NotifyClients
  @RequestMapping(value = "/customer/{customerId}/activity/{activityId}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public @ResponseBody ResponseDTO deleteHotelActivity(@PathVariable CustomerDomainEntityId customerId, @PathVariable long activityId) throws Throwable {
    
    return hotelService.deleteHotelActivity(customerId, activityId);
  }

  @RequestMapping(value = "/customer/{customerId}/activity/{activityId}", method = RequestMethod.POST)//, headers ="Accept:*/*")
  public @ResponseBody
  HotelActivityDTO addUpdateActivity(@PathVariable CustomerDomainEntityId customerId, @PathVariable long activityId, @RequestBody HotelActivityDTO activityDto) throws Throwable {

//    activityDto.setInitId(activityId);
    HotelActivityDTO out = hotelService.addUpdateHotelActivity(customerId, activityDto);
    return out;
  }

//  @NotifyClients
//  @SendTo("/activitytopic/message")
//  @RequestMapping(value = "/customer/activity", method = HttpMethod.PUT, headers ="Accept:*/*")
//  public @ResponseBody HotelActivityDto addActivity(@RequestBody HotelActivityDto activityDto) {
////    activityDto.setInitId(activityId);
//    HotelActivityDto out = hotelService.addUpdateHotelActivity(activityDto.getSenderId(), activityDto);
//    return out;
//  }

}
