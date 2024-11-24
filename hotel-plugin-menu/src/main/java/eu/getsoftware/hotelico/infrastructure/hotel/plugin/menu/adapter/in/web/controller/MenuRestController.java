package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.api.adapter.in.web.controller.IMenuApiController;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.menu.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuRestController implements IMenuApiController
{
	
//	@RequestMapping(value = "/items", method = RequestMethod.GET) in api interface
	public @ResponseBody List<MenuDTO> getItems() {
		return getDemoItems();
	}

	public List<MenuDTO> getDemoItems()
	{
		MenuDTO dto = MenuDTO.builder().id(123L).build();
		return List.of(dto);
	}
	
}
