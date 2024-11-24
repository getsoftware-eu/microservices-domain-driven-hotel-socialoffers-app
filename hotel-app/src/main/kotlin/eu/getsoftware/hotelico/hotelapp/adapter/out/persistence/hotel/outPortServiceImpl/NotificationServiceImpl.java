package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotel.outPortServiceImpl.microservice.WebSocketNotificationService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.INotificationService;
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
    public void notificateAboutEntityEvent(CustomerDTO dto, IHotelEvent event, String eventContent, long entityId) {

        CustomerNotificationDTO notification = new CustomerNotificationDTO(dto.getInitId());
                
        webSocketService.produceSimpWebsocketMessage(AppConfigProperties.SOCKET_NOTIFICATION_TOPIC + dto.getInitId(), notification);

    }

    @Override
    public void createAndSendNotification(long id, HotelEvent hotelEvent) {
        
    }

    @Override
    public CustomerNotificationDTO getLastNotification(long customerId, boolean pushRequest) {
        return null;
    }

    @Override
    public void sendNotificationToCustomerOrGuest(Object o, CustomerDomainEntityId id, HotelEvent hotelEvent) {
        
    }


    @Override
    public void broadcastActivityNotification(HotelActivityDTO hotelActivityDto) {

    }

    @Override
    public void broadcastWallNotification(WallPostDTO wallPostDto) {

    }
}
