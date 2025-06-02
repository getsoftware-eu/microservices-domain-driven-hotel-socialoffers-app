package eu.getsoftware.hotelico.service.booking.adapter.in.web.controller.hotel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitGuest {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Hotel App";
    }
}
