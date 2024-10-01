package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.utils.IHotelEvent;

public interface INotificationService {
    
    void notificateAboutEntityEvent(CustomerDTO dto, IHotelEvent event, String eventContent, long entityId);

}
