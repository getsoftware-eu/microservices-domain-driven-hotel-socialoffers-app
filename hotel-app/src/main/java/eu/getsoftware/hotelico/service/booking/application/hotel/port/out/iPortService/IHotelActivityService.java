package eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.utils.ReorderAction;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.checkin.model.HotelActivityDBEntity;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.ResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IHotelActivityService {

    HotelActivityDTO addUpdateHotelActivity(long customerId, HotelActivityDTO hotelActivityDto);

    @Transactional
    CustomerDTO addGuestAction(long guestCustomerId, String action, Long hotelId, CustomerDTO guestDto);

    @Transactional
    void reorderActivitiesInHotel(ReorderAction action, HotelActivityDBEntity activity);

    @Transactional
    HotelActivityDTO addActivityAction(long customerId, long activityId, String action);

    @Transactional
    HotelActivityDTO updateHotelActivity(HotelActivityDTO hotelActivityDto);

    @Transactional
    List<HotelActivityDTO> getHotelActivitiesByHotelId(long requesterId, long hotelId);

    @Transactional
    HotelActivityDTO convertActivityToDto(HotelActivityDBEntity hotelActivity, CustomerDBEntity requester);

    @Transactional
    List<HotelActivityDTO> getHotelActivitiesBySenderAndHotelId(long senderId, long hotelId);

    @Transactional
    HotelActivityDTO getHotelActivityById(long requesterId, long activityId);


    @Transactional
    ResponseDTO deleteHotelActivity(long customerId, long activityId);

}
