package eu.getsoftware.hotelico.service.booking.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.common.adapter.in.web.controller.BasicController;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.LastMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful webservice
 * REST Methoden with params for service execution
 */

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StartController extends BasicController
{
    private final IHotelService hotelService;    
    
    private final LastMessagesService lastMessagesService;
    
    /**
     * start.jsp view file
     */
    @GetMapping
    public String start() {

//        model.addAttribute("virtualHotelId", lastMessagesService.getInitHotelId());
//        model.addAttribute("host", AppConfigProperties.HOST);
//        model.addAttribute("hostSuffix", AppConfigProperties.HOST_SUFFIX);
//        
//        model.addAttribute("demoHotelId", lastMessagesService.getDemoHotelId());
//        model.addAttribute("demoHotelCode", AppConfigProperties.HOTEL_DEMO_CODE);
        
        return "ok";
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
