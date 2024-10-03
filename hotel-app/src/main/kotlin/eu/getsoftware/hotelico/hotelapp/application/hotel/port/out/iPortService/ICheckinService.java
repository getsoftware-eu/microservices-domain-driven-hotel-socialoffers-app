package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.domain.customer.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerHotelCheckin;

import java.util.Date;
import java.util.List;

public interface ICheckinService {
    List<ICustomerHotelCheckin> getActiveByCustomerId(long id, Date date);

    Date getLastByCustomerAndHotelId(long id, long id1);

    ICustomerHotelCheckin save(ICustomerHotelCheckin customerHotelCheckin);

    List<ICustomerRootEntity> getStaffByHotelId(long hotelId);

    ICustomerHotelCheckin createCheckin();

    Integer getActiveCountByHotelId(long receiverHotelId, Date date);
}
