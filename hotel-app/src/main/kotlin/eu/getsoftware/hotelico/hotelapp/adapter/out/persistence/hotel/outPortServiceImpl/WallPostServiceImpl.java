package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelWallPost;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWallpostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WallPostServiceImpl implements IWallpostService {

    private final HotelServiceImpl hotelService;
    
    @Override
    public void sendNotificationWallpostOnDemand(CustomerDTO customerEntity, LocalDate lastSameHotelCheckin, HotelDTO hotelRootEntity, CustomerDTO staffSender) {
        
        // ### Notificate Wall
        if(lastSameHotelCheckin ==null || lastSameHotelCheckin.getDayOfYear() != LocalDateTime.now().getDayOfYear() )
        {

            WallPostDTO checkinNotificationWallDto = new WallPostDTO(hotelRootEntity.getDomainEntityId(), staffSender.getDomainEntityId());

            checkinNotificationWallDto.getSpecialContent().put("customerId", String.valueOf(customerEntity.getId()));
//								checkinNotificationWallDto.setsetValidUntil(new DateTime().plusDays(1).toDate());
            checkinNotificationWallDto.setSequenceId(System.currentTimeMillis());
            checkinNotificationWallDto.setMessage("New guest at our hotel:");

            hotelService.addUpdateWallPost(checkinNotificationWallDto);
        }
    }

    @Override
    public WallPostDTO updateWallPost(WallPostDTO wallPostDto) {
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
    public WallPostDTO addUpdateWallPost(WallPostDTO wallPostDto) {
        return null;
    }

    @Override
    public List<WallPostDTO> getWallPostsByHotelId(long hotelId) {
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
