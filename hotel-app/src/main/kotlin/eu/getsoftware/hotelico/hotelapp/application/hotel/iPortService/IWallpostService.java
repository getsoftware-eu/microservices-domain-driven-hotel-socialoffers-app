package eu.getsoftware.hotelico.hotelapp.application.hotel.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelWallPost;
import eu.getsoftware.hotelico.hotelapp.application.hotel.infrastructure.dto.WallPostDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IWallpostService {
    WallPostDTO updateWallPost(WallPostDTO wallPostDto);

    @Transactional
    WallPostDTO addWallPost(WallPostDTO wallPostDto);


    WallPostDTO convertWallToDto(HotelWallPost wallPost);

    WallPostDTO addUpdateWallPost(WallPostDTO wallPostDto);
    
    List<WallPostDTO> getWallPostsByHotelId(long hotelId);

    WallPostDTO getWallPostById(long wallPostId);

    List<CustomerDTO> getWallPostParticipantsByHotelId(long requesterId, long hotelId);

}
