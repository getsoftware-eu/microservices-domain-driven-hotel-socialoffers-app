package eu.getsoftware.hotelico.service.booking.application.customer.port.in;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerRequestDTO;
import eu.getsoftware.hotelico.clients.common.domain.ids.CustomerDomainEntityId;

public interface CustomerDtoUseCase
{
	CustomerDTO getCustomerById(CustomerDomainEntityId customerId);
	
	/**
	 * Set this dto info personally to me
	 * @param requester
	 * @param dto
	 * @return
	 */
	CustomerDTO setDtoLastMessageWithRequester(CustomerRequestDTO requester, CustomerDTO dto);
	
}
