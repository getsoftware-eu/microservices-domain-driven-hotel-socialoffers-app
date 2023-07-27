package eu.getsoftware.hotelico.customer.infrastructure.service;

import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;
import org.springframework.transaction.annotation.Transactional;

public class CustomerDtoService
{
	/**
	 * Set this dto info personally to me
	 * @param requester
	 * @param dto
	 * @return
	 */
	@Transactional CustomerDTO setDtoLastMessageWithRequester(CustomerRootEntity requester, CustomerDTO dto);
	
}
