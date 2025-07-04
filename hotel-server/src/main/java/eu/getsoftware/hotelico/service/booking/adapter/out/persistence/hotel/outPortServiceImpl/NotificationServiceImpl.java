package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents.InnerDomainEvent;
import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.InnerHotelEvent;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.outPortServiceImpl.microservice.WebSocketNotificationService;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private final WebSocketNotificationService webSocketService;

//    public void notificateEvent(CustomerDTO dto, CustomerNotificationDTO receiverNotification) {
//
//        if(dto.getHotelId()>0)
//        {
//            receiverNotification.setCustomerEvent(dto.getId(), dto.getHotelId(), event, eventContent, entityId);
//
//            //                if(event.getPushUrl()!=null)
//            //                {
//            //                    receiverNotification.setPushCustomerEvent(event.getPushTitle(), eventContent, event.getPushUrl(), event.getPushIcon());
//            //                    cacheService.setLastPushNotifiation(nextOnlineCustomerId, receiverNotification);
//            //                    sendPushRequest(nextOnlineCustomerId);
//            //                }
//        }
//
//        webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + nextOnlineCustomerId, receiverNotification);
//
//    }

    @Override
    public void notificateAboutEntityEvent(CustomerDTO dto, InnerDomainEvent event, String eventContent, EntityIdentifier entityId) {
        CustomerNotificationDTO notification = new CustomerNotificationDTO();

        webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + dto.getDomainEntityId(), notification);

    }

    @Override
    public void createAndSendNotification(long id, InnerHotelEvent hotelEvent) {
        
    }

    @Override
    public CustomerNotificationDTO getLastNotification(long customerId, boolean pushRequest) {
        return null;
    }

    @Override
    public void sendNotificationToCustomerOrGuest(Object o, CustomerDomainEntityId id, InnerHotelEvent hotelEvent) {
        
    }


    @Override
    public void broadcastActivityNotification(HotelActivityDTO hotelActivityDto) {

    }

    @Override
    public void broadcastWallNotification(WallPostDTO wallPostDto) {

    }
}
