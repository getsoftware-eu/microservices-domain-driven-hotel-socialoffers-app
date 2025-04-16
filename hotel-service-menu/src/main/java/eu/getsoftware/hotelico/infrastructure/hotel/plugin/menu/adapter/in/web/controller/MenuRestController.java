package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.openapi.generated.menu.MenuApi;
import eu.getsoftware.hotelico.clients.openapi.generated.menu.dto.MenuRestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
/*public*/ class MenuRestController implements MenuApi //, IMenuApiController
{
	
//	@RequestMapping(value = "/items", method = RequestMethod.GET) in api interface
  	@ResponseBody
  	public ResponseEntity<List<MenuRestDTO>> getItems() {
		  List<MenuRestDTO> demoItems = getDemoItems();
		  return ResponseEntity.of(Optional.of(demoItems));
	}

	private List<MenuRestDTO> getDemoItems()
	{
		MenuRestDTO dto = new MenuRestDTO(123L, 456L, 789L);
		return List.of(dto);
	}
	
}
