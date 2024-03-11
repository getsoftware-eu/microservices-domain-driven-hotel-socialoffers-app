package eu.getsoftware.hotelico.hotel.application.iService;

import eu.getsoftware.hotelico.checkin.domain.CustomerHotelCheckin;
import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
public interface CheckinService
{
	CustomerDTO updateCheckin(CustomerDTO customerDto);
	
	CustomerDTO setCustomerCheckin(CustomerDTO customerDto, CustomerRootEntity customerEntity);
	
	/**
	 * check the current checkin of logging user. It will find the current hotel of user
	 * @param dto
	 * @return
	 */
	@Transactional CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, CustomerHotelCheckin validCheckin);
	
	@Transactional
	ICustomerRootEntity getStaffbyHotelId(long hotelId);

}
