package eu.getsoftware.hotelico.hotel.application.port.in.iService;

import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.common.utils.ReorderAction;
import eu.getsoftware.hotelico.customer.adapter.out.persistence.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotel.application.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotel.application.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelCustomer.adapter.out.persistence.HotelActivity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IHotelActivityService {


    HotelActivityDTO addUpdateHotelActivity(long customerId, HotelActivityDTO hotelActivityDto);

    @Transactional
    CustomerDTO addGuestAction(long guestCustomerId, String action, Long hotelId, CustomerDTO guestDto);

    @Transactional
    void reorderActivitiesInHotel(ReorderAction action, HotelActivity activity);

    @Transactional
    HotelActivityDTO addActivityAction(long customerId, long activityId, String action);

    @Transactional
    HotelActivityDTO updateHotelActivity(HotelActivityDTO hotelActivityDto);

    @Transactional
    List<HotelActivityDTO> getHotelActivitiesByHotelId(long requesterId, long hotelId);

    @Transactional
    HotelActivityDTO convertActivityToDto(HotelActivity hotelActivity, CustomerRootEntity requester);

    @Transactional
    List<HotelActivityDTO> getHotelActivitiesBySenderAndHotelId(long senderId, long hotelId);

    @Transactional
    HotelActivityDTO getHotelActivityById(long requesterId, long activityId);


    @Transactional
    ResponseDTO deleteHotelActivity(long customerId, long activityId);

}
