package eu.getsoftware.hotelico.hotelapp.application.multiDomainApplicationCheckinService.port.in;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerRequestDTO;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerHotelCheckin;
import org.springframework.transaction.annotation.Transactional;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
public interface CheckinUseCase
{
//	CustomerDTO updateCheckin(CustomerRequestDTO customerDto);
	
	CustomerDTO createCustomerCheckinFromRequest(CustomerRequestDTO customerRequestDto);
	
	/**
	 * check the current checkin of logging user. It will find the current hotel of user
	 * @param dto
	 * @return
	 */
	@Transactional 
	CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, ICustomerHotelCheckin validCheckin);
	
	@Transactional
	CustomerDTO getStaffbyHotelId(long hotelId);

}
