package eu.getsoftware.hotelico.hotel.application.iService;

import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotel.domain.HotelWallPost;
import eu.getsoftware.hotelico.hotel.infrastructure.dto.WallPostDTO;
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
