package eu.getsoftware.hotelico.hotelapp.application.hotel.usecase.checkin;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.hotelCustomer.model.CustomerHotelCheckin;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
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
