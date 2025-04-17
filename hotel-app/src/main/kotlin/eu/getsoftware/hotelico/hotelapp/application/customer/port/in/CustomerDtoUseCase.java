package eu.getsoftware.hotelico.hotelapp.application.customer.port.in;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerRequestDTO;

public interface CustomerDtoUseCase
{
	/**
	 * Set this dto info personally to me
	 * @param requester
	 * @param dto
	 * @return
	 */
	CustomerDTO setDtoLastMessageWithRequester(CustomerRequestDTO requester, CustomerDTO dto);
	
}
