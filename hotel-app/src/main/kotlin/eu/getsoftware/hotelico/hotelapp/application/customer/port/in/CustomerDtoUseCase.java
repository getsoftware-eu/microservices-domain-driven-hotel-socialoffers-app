package eu.getsoftware.hotelico.hotelapp.application.customer.port.in;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerRequestDTO;

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
