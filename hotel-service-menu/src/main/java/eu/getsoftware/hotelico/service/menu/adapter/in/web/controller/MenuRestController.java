package eu.getsoftware.hotelico.service.menu.adapter.in.web.controller;

//eu: generated openapi DTO
import eu.getsoftware.hotelico.clients.openapi.generated.menu.MenuApi;
import eu.getsoftware.hotelico.clients.openapi.generated.menu.dto.MenuRestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
/*public*/ class MenuRestController implements MenuApi
{
	
  	@ResponseBody
  	public ResponseEntity<List<MenuRestDTO>> getItems() {
		  
	  	List<MenuRestDTO> demoItems = getDemoItems();
		return new ResponseEntity<>(demoItems, HttpStatus.OK);
	}

	private List<MenuRestDTO> getDemoItems()
	{
		MenuRestDTO dto = new MenuRestDTO(123L, 456L, 789L);
		return List.of(dto);
	}
	
}
