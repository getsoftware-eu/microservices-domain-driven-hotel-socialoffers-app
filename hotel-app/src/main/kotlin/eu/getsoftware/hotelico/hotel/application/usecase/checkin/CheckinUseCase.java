package eu.getsoftware.hotelico.hotel.application.usecase.checkin;

import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.adapter.out.persistence.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelCustomer.adapter.out.persistence.CustomerHotelCheckin;
import org.springframework.transaction.annotation.Transactional;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
public interface CheckinUseCase
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
