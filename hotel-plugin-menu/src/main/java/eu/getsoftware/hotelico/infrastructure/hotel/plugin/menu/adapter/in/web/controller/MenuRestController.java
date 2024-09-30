package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.in.web.controller;

import eu.getsoftware.hotelico.adapter.in.web.controller.api.IMenuApiController;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.menu.MenuDTO;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.application.infrastructure.service.AsyncMSCommunicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuRestController implements IMenuApiController
{
	private final AsyncMSCommunicationService asyncMSCommunicationService;
	
	public MenuRestController(AsyncMSCommunicationService asyncMSCommunicationService)
	{
		this.asyncMSCommunicationService = asyncMSCommunicationService;
	}
	
//	@RequestMapping(value = "/items", method = RequestMethod.GET) in api interface
	public @ResponseBody List<MenuDTO> getItems() {
		return asyncMSCommunicationService.getItems();
	}
	
}
