package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.common.adapter.in.web.controller.BasicController;
import eu.getsoftware.hotelico.clients.common.utils.ControllerUtils;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * RESTful webservice
 * REST Methoden with params for service execution
 */

@Controller
@RequestMapping("/")
public class StartController  extends BasicController
{
    @Autowired
    private IHotelService hotelService;    
    
    @Autowired
    private LastMessagesService lastMessagesService;
    
    /**
     * start.jsp view file
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewUsers(ModelMap model) {

        model.addAttribute("virtualHotelId", lastMessagesService.getInitHotelId());
        model.addAttribute("host", ControllerUtils.HOST);
        model.addAttribute("hostSuffix", ControllerUtils.HOST_SUFFIX);
        
        model.addAttribute("demoHotelId", lastMessagesService.getDemoHotelId());
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
