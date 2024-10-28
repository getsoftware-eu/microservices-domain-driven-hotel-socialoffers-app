package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.INotificationService;

public class NotificationServiceImpl implements INotificationService {
    

    public void notificateEvent(CustomerDTO dto, CustomerNotificationDTO receiverNotification) {

        if(dto.getHotelId()>0)
        {
            receiverNotification.setCustomerEvent(dto.getId(), dto.getHotelId(), event, eventContent, entityId);

            //                if(event.getPushUrl()!=null)
            //                {
            //                    receiverNotification.setPushCustomerEvent(event.getPushTitle(), eventContent, event.getPushUrl(), event.getPushIcon());
            //                    cacheService.setLastPushNotifiation(nextOnlineCustomerId, receiverNotification);
            //                    sendPushRequest(nextOnlineCustomerId);
            //                }
        }

        webSocketService.produceSimpWebsocketMessage(ControllerUtils.SOCKET_NOTIFICATION_TOPIC + nextOnlineCustomerId, receiverNotification);

    }
}
