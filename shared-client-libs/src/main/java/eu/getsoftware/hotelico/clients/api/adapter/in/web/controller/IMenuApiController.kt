package eu.getsoftware.hotelico.clients.api.adapter.`in`.web.controller

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.menu.MenuDTO
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

interface IMenuApiController {

    @RequestMapping(value = ["/items"], method = [RequestMethod.GET])
    fun getItems(): List<MenuDTO> 

}