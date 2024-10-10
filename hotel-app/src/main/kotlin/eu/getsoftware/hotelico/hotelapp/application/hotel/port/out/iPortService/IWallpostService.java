package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.HotelDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelWallPost;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface IWallpostService {
    IHotelWallPost updateWallPost(IHotelWallPost wallPostDto);

    @Transactional
    IHotelWallPost addWallPost(IHotelWallPost wallPostDto);

    IHotelWallPost convertWallToDto(IHotelWallPost wallPost);

    IHotelWallPost addUpdateWallPost(IHotelWallPost wallPostDto);
    
    List<IHotelWallPost> getWallPostsByHotelId(long hotelId);

    IHotelWallPost getWallPostById(long wallPostId);

    List<CustomerDTO> getWallPostParticipantsByHotelId(long requesterId, long hotelId);

    void sendNotificationWallpostOnDemand(CustomerDTO customerEntity, Date lastSameHotelCheckin, HotelDTO hotelRootEntity, CustomerDTO staffSender);
}
