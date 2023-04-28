package eu.getsoftware.hotelico.hotel.infrastructure.service;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.checkin.domain.CustomerHotelCheckin;
import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
public interface CheckinService
{
	@Transactional CustomerDTO updateCheckin(CustomerDTO customerDto);
	
	@Transactional CustomerDTO setCustomerCheckin(CustomerDTO customerDto, CustomerRootEntity customerEntity);
	
	/**
	 * check the current checkin of logging user. It will find the current hotel of user
	 * @param dto
	 * @return
	 */
	@Transactional CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, CustomerHotelCheckin validCheckin);
	
	@Transactional
	CustomerRootEntity getStaffbyHotelId(long hotelId);

}
