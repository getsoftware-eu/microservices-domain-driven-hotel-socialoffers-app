package eu.getsoftware.hotelico.hotelapp.adapter.in.web.controller.hotel;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.BasicHotelException;
import eu.getsoftware.hotelico.clients.common.adapter.in.web.controller.BasicController;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.aspects.NotifyClients;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWallpostService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.Point2D;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Timed("sensorMap.controller") //eugen: profiler
@RepositoryRestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController extends BasicController
{
    private final IHotelService hotelService;    
    private final IWallpostService hotelWallpostService;
    private final LastMessagesService lastMessagesService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewHotels() {
        return "hotels";
    }
    
    @RequestMapping(value = "/customer/hotel", method = RequestMethod.GET)
    public List<HotelDTO> getHotels() {
        return hotelService.getHotels();
    }


//    /**
//     * eu: create a link to created entity :)))
//     * @param inputHotelDTO
//     * @param persistentEntityResourceAssembler
//     * @return
//     */
//    @RequestMapping(method = POST, value = "/hotels")
//    @ResponseBody
//    public ResponseEntity<Resource<HotelDTO>> createNewHotel(@RequestBody HotelDTO inputHotelDTO,
//                                                            PersistentEntityResourceAssembler persistentEntityResourceAssembler
//    ) {
//
//        HotelDTO createdHotel = createWithSkuHandling(inputHotelDTO);
//
//        Resource<HotelDTO> hotelResource = new Resource<>(createdHotel);
//        hotelResource.add(persistentEntityResourceAssembler.getSelfLinkFor(createdHotel));
//        
//        URI location = linkTo(HotelController.class)
//                .slash("/hotels")
//                .slash(createdHotel.getId())
//                .toUri();
//        return ResponseEntity.created(location).body(hotelResource);
//    }
    
    @RequestMapping(value = "/customer/{customerId}/hotel/{hotelId}", method = RequestMethod.GET)
    public HotelDTO get(@PathVariable long customerId, @PathVariable HotelDomainEntityId hotelId, final HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        HotelDTO out = hotelService.getHotelByDomainId(hotelId);
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
        CustomerDTO out = CustomerDTO.builder().build(); //= hotelActivityService.addGuestAction(guestCustomerId, action, hotelId, guestDto);
        return out;
    }   
    
    @RequestMapping(value = "/customer/{customerId}/hotel", method = RequestMethod.GET)
    public List<HotelDTO> getAll(@PathVariable long customerId, final HttpServletResponse response) {
        return hotelService.getHotels();
    }   
    
    @RequestMapping(value = "/customer/{customerId}/hotelCities", method = RequestMethod.GET)
    public List<HotelDTO> getHotelCities(@PathVariable CustomerDomainEntityId customerId, final HttpServletResponse response) {
        return hotelService.getHotelCities(customerId);
    } 
    
    @RequestMapping(value = "/customer/{customerId}/hotel/city/{city}", method = RequestMethod.GET)
    public List<HotelDTO> getHotelCities(@PathVariable CustomerDomainEntityId customerId, @PathVariable String city, final HttpServletResponse response) throws BasicHotelException
    {
        return hotelService.getHotelsByCity(customerId, city);
    }  
    
    @RequestMapping(value = "/customer/hotel/{hotelId}", method = RequestMethod.GET)
    public HotelDTO getWithoutCustomer(@PathVariable HotelDomainEntityId hotelId, final HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        HotelDTO out = hotelService.getHotelById(hotelId);
        return out;
    } 
    
   
     @RequestMapping(value = "/wall/messages/hotelId/{id}", method = RequestMethod.GET)
    public List<WallPostDTO> getWallPostsByHotelId(@PathVariable int id) {
        List<WallPostDTO> out = hotelWallpostService.getWallPostsByHotelId(id);
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
        WallPostDTO out = hotelWallpostService.updateWallPost(wallPostDto);
        return out;
    }
    
    @NotifyClients
    @RequestMapping(value = "/hotels", method = POST)
    public HotelDTO add(@RequestBody HotelDTO hotelDto) {
        HotelDTO out = hotelService.addHotel(hotelDto);
        return out;
    }
    
    
    
    @NotifyClients
    @RequestMapping(value = "/wall", method = POST)
    public WallPostDTO addWallPost(@RequestBody WallPostDTO wallPostDto) {
        WallPostDTO out = hotelWallpostService.addUpdateWallPost(wallPostDto);
        return out;
    }

    @NotifyClients
    @RequestMapping(value = "customer/{customerId}/hotel/{hotelId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDTO delete(@PathVariable CustomerDomainEntityId customerId, @PathVariable HotelDomainEntityId hotelId) {
        
		return hotelService.deleteHotel(hotelId, customerId);
    }


    @RequestMapping(value = "/getGpsHotelCity/requesterId/{requesterId}/lat/{lat}/lon/{lon}", method = RequestMethod.GET)
    public @ResponseBody
    CustomerNotificationDTO getGpsHotelCity(@PathVariable CustomerDomainEntityId requesterId, @PathVariable String lat, @PathVariable String lon, HttpSession httpSession) {

        double latPoint = Double.parseDouble(lat);
        double lonPoint = Double.parseDouble(lon);

        Point2D.Double latLonPoint = new Point2D.Double(latPoint , lonPoint);

        lastMessagesService.addGuestGpsPosition(requesterId, latLonPoint);
        
        String gpsCity = hotelService.getGpsCity(latLonPoint);

        if(gpsCity!=null)
        {
            CustomerNotificationDTO notificationDto = new CustomerNotificationDTO();
            notificationDto.setSocketPushCustomerEvent("gpsCity", gpsCity, "", "", "");
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
