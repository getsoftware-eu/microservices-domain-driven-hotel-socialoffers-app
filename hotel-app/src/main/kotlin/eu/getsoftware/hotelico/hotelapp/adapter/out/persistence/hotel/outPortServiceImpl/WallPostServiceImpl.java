package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.model.IHotelRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IWallpostService;

import java.time.LocalDateTime;
import java.util.Date;

import static eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.convertToLocalDateTime;

public class WallPostServiceImpl implements IWallpostService {

    void sendNotificationWallpostOnDemand(ICustomerRootEntity customerEntity, Date lastSameHotelCheckin, IHotelRootEntity hotelRootEntity, ICustomerRootEntity staffSender) {
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
}
