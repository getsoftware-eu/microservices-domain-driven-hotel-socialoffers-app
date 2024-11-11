package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.WallPostDTO;

public interface INotificationService {
    
    void notificateAboutEntityEvent(CustomerDTO dto, IHotelEvent event, String eventContent, long entityId);

    void createAndSendNotification(long id, HotelEvent hotelEvent);

    CustomerNotificationDTO getLastNotification(long customerId, boolean pushRequest);

    void sendNotificationToCustomerOrGuest(Object o, CustomerDomainEntityId id, HotelEvent hotelEvent);

    void broadcastActivityNotification(HotelActivityDTO hotelActivityDto);

    void broadcastWallNotification(WallPostDTO wallPostDto);
}
