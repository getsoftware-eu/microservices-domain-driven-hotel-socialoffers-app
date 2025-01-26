package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.menu.MenuItemDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.menu.MenuOrderDTO;
import eu.getsoftware.hotelico.clients.common.adapter.in.web.controller.BasicController;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.application.iPortService.IMenuPortService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/menu")
/*public*/ class MenuController extends BasicController
{

//  @Autowired
//  private HotelService hotelService;  
	
  @Autowired
  private IMenuPortService menuService;
	
  @RequestMapping(method = RequestMethod.GET)
  public String viewApplication() {
    return "menu";
  }

  @RequestMapping(value = "/order/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/orderId/{orderId}/showClosed/{showClosed}", method = RequestMethod.GET)
	public @ResponseBody
	List<MenuOrderDTO> getMenus(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable long orderId, @PathVariable boolean showClosed) {
	  long requesterId = 123;	
      return menuService.getActiveMenusByCustomerId( customerId, hotelId, cafeId, orderId, showClosed);
	}
		
	@RequestMapping(value = "/item/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/menuItemId/{menuItemId}", method = RequestMethod.GET)
	public @ResponseBody
	List<MenuItemDTO> getHotelMenuItems(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable int menuItemId) {
		return menuService.getMenuItemsByHotelId(customerId, hotelId, cafeId);
	}
	
	@RequestMapping(value = "/item/customer/{customerId}/hotelId/{hotelId}/cafeId/{cafeId}/reorder/{reorder}", method = RequestMethod.GET)
	public @ResponseBody
	List<MenuItemDTO> getReorderMenuItems(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable String reorder) {
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
  public @ResponseBody MenuItemDTO deleteMenuItem(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable int menuItemId) {
    
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
  MenuItemDTO addUpdateMenuItem(@PathVariable long customerId, @PathVariable long hotelId, @PathVariable long cafeId, @PathVariable int itemId, @RequestBody MenuItemDTO menuItemDto) {

//    activityDto.setInitId(activityId);
	  MenuItemDTO out = menuService.addUpdateMenuItem(customerId, hotelId, cafeId, itemId, menuItemDto);
    
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
