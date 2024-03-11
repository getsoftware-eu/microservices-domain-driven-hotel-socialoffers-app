package eu.getsoftware.hotelico.apiController

import eu.getsoftware.hotelico.clients.infrastructure.menu.MenuDTO
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface IMenuApiController {

    @RequestMapping(value = ["/items"], method = [RequestMethod.GET])
    fun getItems(): List<MenuDTO> 

}