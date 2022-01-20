package de.hotelico.service;

import org.springframework.transaction.annotation.Transactional;

import de.hotelico.dto.CustomerDTO;
import de.hotelico.model.Customer;
import de.hotelico.model.CustomerHotelCheckin;

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
