package de.hotelico.controller;

import de.hotelico.model.Hotel;
import de.hotelico.repository.HotelRepository;
import de.hotelico.service.CacheService;
import de.hotelico.service.HotelService;
import de.hotelico.service.UserService;
import de.hotelico.service.impl.HotelServiceImpl;
import de.hotelico.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.linkedin.api.LinkedIn;
//import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.inject.Inject;

/**
 * RESTful webservice
 * REST Methoden with params for service execution
 */

@Controller
@RequestMapping("/")
public class StartController  extends BasicController
{
    @Autowired
    private HotelService hotelService;    
    
    @Autowired
    private CacheService cacheService;
    
    /**
     * start.jsp view file
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewUsers(ModelMap model) {

        model.addAttribute("virtualHotelId", cacheService.getInitHotelId());
        model.addAttribute("host", ControllerUtils.HOST);
        model.addAttribute("hostSuffix", ControllerUtils.HOST_SUFFIX);
        
        model.addAttribute("demoHotelId", cacheService.getDemoHotelId());
        model.addAttribute("demoHotelCode", ControllerUtils.HOTEL_DEMO_CODE);
        
        return "start";
    }




//    private final LinkedIn linkedIn;

//    @Inject
//    public StartController(LinkedIn linkedIn) {
//        this.linkedIn = linkedIn;
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String home(Model model) {
//        LinkedInProfileFull profile = linkedIn.profileOperations().getUserProfileFull();
//        model.addAttribute("born", profile.getDateOfBirth());
//        return "start";
//    }

   
}
