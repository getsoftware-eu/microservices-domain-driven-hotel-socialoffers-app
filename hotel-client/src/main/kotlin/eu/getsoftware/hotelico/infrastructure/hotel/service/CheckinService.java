package eu.getsoftware.hotelico.infrastructure.hotel.service;

import org.springframework.transaction.annotation.Transactional;

import eu.getsoftware.hotelico.domain.hotel.CustomerHotelCheckin;
import eu.getsoftware.hotelico.hotel.model.CustomerEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.dto.CustomerDTO;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
public interface CheckinService
{
	@Transactional CustomerDTO updateCheckin(CustomerDTO customerDto);
	
	@Transactional CustomerDTO setCustomerCheckin(CustomerDTO customerDto, CustomerEntity customerEntity);
	
	/**
	 * check the current checkin of logging user. It will find the current hotel of user
	 * @param dto
	 * @return
	 */
	@Transactional CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, CustomerHotelCheckin validCheckin);
	
	@Transactional
	CustomerEntity getStaffbyHotelId(long hotelId);

}
