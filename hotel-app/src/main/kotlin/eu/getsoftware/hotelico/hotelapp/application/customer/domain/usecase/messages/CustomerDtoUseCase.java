package eu.getsoftware.hotelico.hotelapp.application.customer.domain.usecase.messages;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.hotelapp.application.customer.common.iEntity.ICustomerEntity;

public interface CustomerDtoUseCase
{
	/**
	 * Set this dto info personally to me
	 * @param requester
	 * @param dto
	 * @return
	 */
	CustomerDTO setDtoLastMessageWithRequester(ICustomerEntity requester, CustomerDTO dto);
	
}
