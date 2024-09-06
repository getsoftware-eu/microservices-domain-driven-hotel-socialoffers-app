package eu.getsoftware.hotelico.customer.application.domain.usecase.messages;

import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.common.iEntity.ICustomerEntity;

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
