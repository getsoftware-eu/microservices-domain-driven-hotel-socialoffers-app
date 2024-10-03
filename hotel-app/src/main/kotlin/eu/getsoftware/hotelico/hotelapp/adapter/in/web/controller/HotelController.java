package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.clients.common.adapter.in.web.controller.BasicController;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.dto.HotelResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.aspects.NotifyClients;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelActivityService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWallpostService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.Point2D;
import java.util.List;

@Timed("sensorMap.controller") //eugen: profiler
@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController extends BasicController
{
    @Autowired
    private IHotelService hotelService;    
    
    @Autowired
    private IHotelActivityService hotelActivityService;    
    
    @Autowired
    private IWallpostService hotelWallpostService;
    
    @Autowired
    private LastMessagesService lastMessagesService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewHotels() {
        return "hotels";
    }

//    @RequestMapping(value = "/hotels", method = RequestMethod.GET)
//    public  List<HotelDto> getHotels() {
//        return hotelService.getHotels();
//    }
    
    @RequestMapping(value = "/customer/hotel", method = RequestMethod.GET)
    public List<HotelResponseDTO> getHotels() {
        return hotelService.getHotels();
    }

    @RequestMapping(value = "/customer/{customerId}/hotel/{hotelId}", method = RequestMethod.GET)
    public HotelResponseDTO get(@PathVariable long customerId, @PathVariable long hotelId, final HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        HotelResponseDTO out = hotelService.getHotelById(hotelId);
        return out;
    }
    
    @ApiOperation(value = "every hotel has its unique code", produces = "application/json")
    @RequestMapping(value = "/customer/{customerId}/hotelCode/{hotelCode}", method = RequestMethod.GET)
    public HotelResponseDTO getHotelByCode(@PathVariable long customerId, @PathVariable String hotelCode, final HttpServletResponse response) {
//        response.setHeader("Cache-Control", "no-cache");
        HotelResponseDTO out = hotelService.getHotelByCode(hotelCode);
        return out;
    }    
    
    @RequestMapping(value = "/action/{action}/hotelId/{hotelId}/customer/{guestCustomerId}", method = RequestMethod.POST)
    public CustomerDTO addHotelGuestAction(@PathVariable String action, @PathVariable Long hotelId, @PathVariable long guestCustomerId, @RequestBody CustomerDTO guestDto, final HttpServletResponse response) {
//        response.setHeader("Cache-Control", "no-cache");
        CustomerDTO out = hotelActivityService.addGuestAction(guestCustomerId, action, hotelId, guestDto);
        return out;
    }   
    
    @RequestMapping(value = "/customer/{customerId}/hotel", method = RequestMethod.GET)
    public List<HotelResponseDTO> getAll(@PathVariable long customerId, final HttpServletResponse response) {
        return hotelService.getHotels();
    }   
    
    @RequestMapping(value = "/customer/{customerId}/hotelCities", method = RequestMethod.GET)
    public List<HotelResponseDTO> getHotelCities(@PathVariable long customerId, final HttpServletResponse response) {
        return hotelService.getHotelCities(customerId);
    } 
    
    @RequestMapping(value = "/customer/{customerId}/hotel/city/{city}", method = RequestMethod.GET)
    public List<HotelResponseDTO> getHotelCities(@PathVariable long customerId, @PathVariable String city, final HttpServletResponse response) throws BasicHotelException
    {
        return hotelService.getHotelsByCity(customerId, city);
    }  
    
    @RequestMapping(value = "/customer/hotel/{hotelId}", method = RequestMethod.GET)
    public HotelResponseDTO getWithoutCustomer(@PathVariable long hotelId, final HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        HotelResponseDTO out = hotelService.getHotelById(hotelId);
        return out;
    } 
    
   
     @RequestMapping(value = "/wall/messages/hotelId/{id}", method = RequestMethod.GET)
    public List<WallPostDTO> getWallPostsByHotelId(@PathVariable int id) {
        List<WallPostDTO> out = hotelWallpostService.getWallPostsByHotelId(id);
        return out;
    }
    
    @NotifyClients
    @RequestMapping(value = "/customer/{customerId}/hotel/{hotelId}", method = RequestMethod.PUT)
    public HotelResponseDTO update(@PathVariable long customerId, @PathVariable long hotelId, @RequestBody HotelResponseDTO hotelDto) {
        
        HotelResponseDTO out = null;
        
        if(hotelId>0)
        {
            hotelDto.setId(hotelId);
            out = hotelService.updateHotel(hotelDto);
        }
        else {
            out = hotelService.addHotel(hotelDto);
        }
        return out;
    } 
    
   

    @NotifyClients
    @RequestMapping(value = "/wall/{id}", method = RequestMethod.PUT)
    public WallPostDTO updateWallPost(@PathVariable long id, @RequestBody WallPostDTO wallPostDto) {
        wallPostDto.setInitId(id);
        WallPostDTO out = hotelWallpostService.updateWallPost(wallPostDto);
        return out;
    }
    
    @NotifyClients
    @RequestMapping(value = "/hotels", method = RequestMethod.POST)
    public HotelResponseDTO add(@RequestBody HotelResponseDTO hotelDto) {
        HotelResponseDTO out = hotelService.addHotel(hotelDto);
        return out;
    }
    
    
    
    @NotifyClients
    @RequestMapping(value = "/wall", method = RequestMethod.POST)
    public WallPostDTO addWallPost(@RequestBody WallPostDTO wallPostDto) {
        WallPostDTO out = hotelWallpostService.addUpdateWallPost(wallPostDto);
        return out;
    }

    @NotifyClients
    @RequestMapping(value = "customer/{customerId}/hotel/{hotelId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDTO delete(@PathVariable long customerId, @PathVariable long hotelId) {
        
		return hotelService.deleteHotel(hotelId, customerId);
    }


    @RequestMapping(value = "/getGpsHotelCity/requesterId/{requesterId}/lat/{lat}/lon/{lon}", method = RequestMethod.GET)
    public @ResponseBody
    CustomerNotificationDTO getGpsHotelCity(@PathVariable long requesterId, @PathVariable String lat, @PathVariable String lon, HttpSession httpSession) {

        double latPoint = Double.parseDouble(lat);
        double lonPoint = Double.parseDouble(lon);

        Point2D.Double latLonPoint = new Point2D.Double(latPoint , lonPoint);

        lastMessagesService.addGuestGpsPosition(requesterId, latLonPoint);
        
        String gpsCity = hotelService.getGpsCity(latLonPoint);

        if(gpsCity!=null)
        {
            CustomerNotificationDTO notificationDto = new CustomerNotificationDTO();
            notificationDto.setPushCustomerEvent("gpsCity", gpsCity, "", "", "");
            return notificationDto;
        }

        return null;
    }
//    @NotifyClients
//    @RequestMapping(value = "/wallpost/{id}", method = RequestMethod.DELETE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteWallPost(@PathVariable int id) {
//        WallPostDto task = new WallPostDto();
//        task.setInitId(id);
//        hotelService.deleteWallPost(task);
//    }
}
