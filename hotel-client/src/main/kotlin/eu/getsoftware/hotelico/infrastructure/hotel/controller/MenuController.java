package eu.getsoftware.hotelico.infrastructure.hotel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.getsoftware.hotelico.infrastructure.hotel.dto.MenuItemDto;
import eu.getsoftware.hotelico.infrastructure.hotel.dto.MenuOrderDTO;
import eu.getsoftware.hotelico.infrastructure.hotel.service.HotelService;
import eu.getsoftware.hotelico.infrastructure.hotel.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController extends BasicController
{

  private Logger logger = LoggerFactory.getLogger(getClass());
  
  @Autowired
  private HotelService hotelService;  
	
  @Autowired
  private MenuService menuService;
	
  @RequestMapping(method = RequestMethod.GET)
  public String viewApplication() {
    return "menu";
  }

  @RequestMapping(value = "/order/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/orderId/{orderId}/showClosed/{showClosed}", method = RequestMethod.GET)
	public @ResponseBody
	List<MenuOrderDTO> getMenus(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable long orderId, @PathVariable boolean showClosed) {
		return menuService.getActiveMenusByCustomerId(customerId, hotelId, cafeId, orderId, showClosed);
	}
		
	@RequestMapping(value = "/item/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/menuItemId/{menuItemId}", method = RequestMethod.GET)
	public @ResponseBody
	List<MenuItemDto> getHotelMenuItems(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable int menuItemId) {
		return menuService.getMenuItemsByHotelId(customerId, hotelId, cafeId);
	}
	
	@RequestMapping(value = "/item/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/reorder/{reorder}", method = RequestMethod.GET)
	public @ResponseBody
	List<MenuItemDto> getReorderMenuItems(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable String reorder) {
		return menuService.getReorderedMenuItems(customerId, hotelId, cafeId, reorder);
	}
	
	
	@RequestMapping(value = "/action/{action}/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/orderId/{orderId}}", method = RequestMethod.GET)
  public @ResponseBody MenuOrderDTO addMenuAction(@PathVariable String action, @PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable long orderId) {
    return menuService.addMenuAction(customerId, orderId, action);
  }
	
  
//  @NotifyClients
  @RequestMapping(value = "/order/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/orderId/{orderId}/showClosed/{showClosed}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public @ResponseBody MenuOrderDTO deleteMenu(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable long orderId, @PathVariable boolean showClosed) {
    
     return menuService.deleteMenuOrder(customerId, orderId);
  }  
	
	@RequestMapping(value = "/item/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/menuItemId/{menuItemId}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public @ResponseBody MenuItemDto deleteMenuItem(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable int menuItemId) {
    
    return  menuService.deleteMenuItem(customerId, menuItemId);
  }

  @RequestMapping(value = "/order/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/orderId/{orderId}/showClosed/{showClosed}", method = RequestMethod.POST)//, headers ="Accept:*/*")
  public @ResponseBody MenuOrderDTO addUpdateMenu(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable long orderId, @PathVariable boolean showClosed, @RequestBody MenuOrderDTO menuOrderDto) {

//    activityDto.setInitId(activityId);
	  MenuOrderDTO out = menuService.addUpdateMenu(customerId, orderId, menuOrderDto);
    return out;
  }  
	
  @RequestMapping(value = "/item/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/menuItemId/{itemId}", method = RequestMethod.POST)//, headers ="Accept:*/*")
  public @ResponseBody
  MenuItemDto addUpdateMenuItem(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable int itemId, @RequestBody MenuItemDto menuItemDto) {

//    activityDto.setInitId(activityId);
	  MenuItemDto out = menuService.addUpdateMenuItem(customerId, hotelId, cafeId, itemId, menuItemDto);
    
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
