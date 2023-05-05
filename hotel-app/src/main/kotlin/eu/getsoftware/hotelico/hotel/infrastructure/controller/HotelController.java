package eu.getsoftware.hotelico.hotel.infrastructure.controller;

import java.awt.geom.Point2D;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import eu.getsoftware.hotelico.clients.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.customer.infrastructure.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.aspects.NotifyClients;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.HotelDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotel.infrastructure.service.FileUploadService;
import eu.getsoftware.hotelico.hotel.infrastructure.service.HotelService;
import eu.getsoftware.hotelico.hotel.infrastructure.service.LastMessagesService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;

@Timed("sensorMap.controller") //eugen: profiler
@RestController
@RequestMapping("/hotels")
public class HotelController extends BasicController
{
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private LastMessagesService lastMessagesService;
    
    @Autowired
    private FileUploadService fileUploadService;
	
	 
	
    @RequestMapping(method = RequestMethod.GET)
    public String viewHotels() {
        return "hotels";
    }

//    @RequestMapping(value = "/hotels", method = RequestMethod.GET)
//    public  List<HotelDto> getHotels() {
//        return hotelService.getHotels();
//    }
    
    @RequestMapping(value = "/customer/hotel", method = RequestMethod.GET)
    public  List<HotelDTO> getHotels() {
        return hotelService.getHotels();
    }

    @RequestMapping(value = "/customer/{customerId}/hotel/{hotelId}", method = RequestMethod.GET)
    public HotelDTO get(@PathVariable long customerId, @PathVariable long hotelId, final HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        HotelDTO out = hotelService.getHotelById(hotelId);
        return out;
    }
    
    @ApiOperation(value = "every hotel has its unique code", produces = "application/json")
    @RequestMapping(value = "/customer/{customerId}/hotelCode/{hotelCode}", method = RequestMethod.GET)
    public HotelDTO getHotelByCode(@PathVariable long customerId, @PathVariable String hotelCode, final HttpServletResponse response) {
//        response.setHeader("Cache-Control", "no-cache");
        HotelDTO out = hotelService.getHotelByCode(hotelCode);
        return out;
    }    
    
    @RequestMapping(value = "/action/{action}/hotelId/{hotelId}/customer/{guestCustomerId}", method = RequestMethod.POST)
    public CustomerDTO addHotelGuestAction(@PathVariable String action, @PathVariable Long hotelId, @PathVariable long guestCustomerId, @RequestBody CustomerDTO guestDto, final HttpServletResponse response) {
//        response.setHeader("Cache-Control", "no-cache");
        CustomerDTO out = hotelService.addGuestAction(guestCustomerId, action, hotelId, guestDto);
        return out;
    }   
    
    @RequestMapping(value = "/customer/{customerId}/hotel", method = RequestMethod.GET)
    public List<HotelDTO> getAll(@PathVariable long customerId, final HttpServletResponse response) {
        return hotelService.getHotels();
    }   
    
    @RequestMapping(value = "/customer/{customerId}/hotelCities", method = RequestMethod.GET)
    public List<HotelDTO> getHotelCities(@PathVariable long customerId, final HttpServletResponse response) {
        return hotelService.getHotelCities(customerId);
    } 
    
    @RequestMapping(value = "/customer/{customerId}/hotel/city/{city}", method = RequestMethod.GET)
    public List<HotelDTO> getHotelCities(@PathVariable long customerId, @PathVariable String city, final HttpServletResponse response) throws BasicHotelException
    {
        return hotelService.getHotelsByCity(customerId, city);
    }  
    
    @RequestMapping(value = "/customer/hotel/{hotelId}", method = RequestMethod.GET)
    public HotelDTO getWithoutCustomer(@PathVariable long hotelId, final HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        HotelDTO out = hotelService.getHotelById(hotelId);
        return out;
    } 
    
   
     @RequestMapping(value = "/wall/messages/hotelId/{id}", method = RequestMethod.GET)
    public List<WallPostDTO> getWallPostsByHotelId(@PathVariable int id) {
        List<WallPostDTO> out = hotelService.getWallPostsByHotelId(id);
        return out;
    }
    
    @NotifyClients
    @RequestMapping(value = "/customer/{customerId}/hotel/{hotelId}", method = RequestMethod.PUT)
    public HotelDTO update(@PathVariable long customerId, @PathVariable long hotelId, @RequestBody HotelDTO hotelDto) {
        
        HotelDTO out = null;
        
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
        WallPostDTO out = hotelService.updateWallPost(wallPostDto);
        return out;
    }
    
    @NotifyClients
    @RequestMapping(value = "/hotels", method = RequestMethod.POST)
    public HotelDTO add(@RequestBody HotelDTO hotelDto) {
        HotelDTO out = hotelService.addHotel(hotelDto);
        return out;
    }
    
    
    
    @NotifyClients
    @RequestMapping(value = "/wall", method = RequestMethod.POST)
    public WallPostDTO addWallPost(@RequestBody WallPostDTO wallPostDto) {
        WallPostDTO out = hotelService.addUpdateWallPost(wallPostDto);
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
