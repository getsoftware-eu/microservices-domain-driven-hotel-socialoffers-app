package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.infrastructure.controller;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.domain.model.MenuItemEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.infrastructure.service.AsyncMSCommunicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuRestController
{
	private final AsyncMSCommunicationService asyncMSCommunicationService;
	
	public MenuRestController(AsyncMSCommunicationService asyncMSCommunicationService)
	{
		this.asyncMSCommunicationService = asyncMSCommunicationService;
	}
	
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public @ResponseBody List<MenuItemEntity> getItems() {
		return asyncMSCommunicationService.getItems();
	}
	
}
