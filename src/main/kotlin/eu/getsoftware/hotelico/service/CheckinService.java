package eu.getsoftware.hotelico.service;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.dto.CustomerDTO;
import eu.getsoftware.hotelico.model.Customer;
import eu.getsoftware.hotelico.model.CustomerHotelCheckin;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
public interface CheckinService
{
	@Transactional CustomerDTO updateCheckin(CustomerDTO customerDto);
	
	@Transactional CustomerDTO setCustomerCheckin(CustomerDTO customerDto, Customer customer);
	
	/**
	 * check the current checkin of logging user. It will find the current hotel of user
	 * @param dto
	 * @return
	 */
	@Transactional CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, CustomerHotelCheckin validCheckin);
	
	@Transactional
	Customer getStaffbyHotelId(long hotelId);

}
