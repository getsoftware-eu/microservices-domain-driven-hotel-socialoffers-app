package eu.getsoftware.hotelico.hotelapp.application.checkin.port.in;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.JsonError;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinDTO;
import eu.getsoftware.hotelico.hotelapp.application.checkin.multiDomainOrchestratorCheckinService.useCase.dto.CheckinRequestDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * In port only DTOs
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
public interface CheckinUseCase
{
//	CustomerDTO updateCheckin(CustomerRequestDTO customerDto);
	
	CheckinDTO validateAndCreateCustomerCheckin(CheckinRequestDTO customerRequestDto) throws JsonError;
	
	/**
	 * check the current checkin of logging user. It will find the current hotel of user
	 * @param dto
	 * @return
	 */
	@Transactional
	CheckinDTO updateOwnDtoCheckinInfo(CheckinRequestDTO dto) throws JsonError;
	
	@Transactional
	CustomerDTO getStaffbyHotelId(long hotelId);

}
