package eu.getsoftware.hotelico.customer.domain.iService;

import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.domain.iEntity.ICustomerEntity;

public class CustomerDtoService
{
	/**
	 * Set this dto info personally to me
	 * @param requester
	 * @param dto
	 * @return
	 */
	CustomerDTO setDtoLastMessageWithRequester(ICustomerEntity requester, CustomerDTO dto);
	
}
