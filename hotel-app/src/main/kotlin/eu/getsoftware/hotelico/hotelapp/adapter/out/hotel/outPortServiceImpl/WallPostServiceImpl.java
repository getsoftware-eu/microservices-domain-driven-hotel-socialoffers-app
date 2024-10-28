package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelWallPost;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWallpostService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.convertToLocalDateTime;

@RequiredArgsConstructor
public class WallPostServiceImpl implements IWallpostService {

    private final HotelServiceImpl hotelService;
    
    @Override
    public void sendNotificationWallpostOnDemand(CustomerDTO customerEntity, Date lastSameHotelCheckin, HotelDTO hotelRootEntity, CustomerDTO staffSender) {
        
        // ### Notificate Wall
        if(lastSameHotelCheckin ==null || convertToLocalDateTime(lastSameHotelCheckin).getDayOfYear() != LocalDateTime.now().getDayOfYear() )
        {

            WallPostDTO checkinNotificationWallDto = new WallPostDTO();

            checkinNotificationWallDto.getSpecialContent().put("customerId", String.valueOf(customerEntity.getId()));
            checkinNotificationWallDto.setHotelId(hotelRootEntity.getId());
            checkinNotificationWallDto.setSenderId(staffSender.getId());
//								checkinNotificationWallDto.setsetValidUntil(new DateTime().plusDays(1).toDate());
            checkinNotificationWallDto.setInitId(new Date().getTime());
            checkinNotificationWallDto.setMessage("New guest at our hotel:");

            hotelService.addUpdateWallPost(checkinNotificationWallDto);
        }
    }

    @Override
    public IHotelWallPost updateWallPost(IHotelWallPost wallPostDto) {
        return null;
    }

    @Override
    public IHotelWallPost addWallPost(IHotelWallPost wallPostDto) {
        return null;
    }

    @Override
    public IHotelWallPost convertWallToDto(IHotelWallPost wallPost) {
        return null;
    }

    @Override
    public IHotelWallPost addUpdateWallPost(IHotelWallPost wallPostDto) {
        return null;
    }

    @Override
    public List<IHotelWallPost> getWallPostsByHotelId(long hotelId) {
        return List.of();
    }

    @Override
    public IHotelWallPost getWallPostById(long wallPostId) {
        return null;
    }

    @Override
    public List<CustomerDTO> getWallPostParticipantsByHotelId(long requesterId, long hotelId) {
        return List.of();
    }

     
}
