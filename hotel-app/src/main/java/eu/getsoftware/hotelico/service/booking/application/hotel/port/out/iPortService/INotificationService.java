package eu.getsoftware.hotelico.service.booking.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.infrastructure.domainevents.innerevents.InnerDomainEvent;
import eu.getsoftware.hotelico.clients.common.domain.EntityIdentifier;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model.InnerHotelEvent;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.CustomerNotificationDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.dto.WallPostDTO;

public interface INotificationService {
    
    void notificateAboutEntityEvent(CustomerDTO dto, InnerDomainEvent event, String eventContent, EntityIdentifier entityId);

    void createAndSendNotification(long id, InnerHotelEvent hotelEvent);

    CustomerNotificationDTO getLastNotification(long customerId, boolean pushRequest);

    void sendNotificationToCustomerOrGuest(Object o, CustomerDomainEntityId id, InnerHotelEvent hotelEvent);

    void broadcastActivityNotification(HotelActivityDTO hotelActivityDto);

    void broadcastWallNotification(WallPostDTO wallPostDto);
}
