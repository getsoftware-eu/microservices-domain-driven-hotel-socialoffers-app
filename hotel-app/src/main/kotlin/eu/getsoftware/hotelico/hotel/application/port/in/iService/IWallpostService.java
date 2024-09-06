package eu.getsoftware.hotelico.hotel.application.port.in.iService;

import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotel.adapter.out.persistence.model.hotel.HotelWallPost;
import eu.getsoftware.hotelico.hotel.application.infrastructure.dto.WallPostDTO;
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
