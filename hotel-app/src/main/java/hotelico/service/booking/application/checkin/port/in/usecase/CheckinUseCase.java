package hotelico.service.booking.application.checkin.port.in.usecase;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.HotelDomainEntityId;
import hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * In port only DTOs
 * Commands идут через UseCase. UseCase - содержит бизнес-логику и взаимодействует с репозиториями.
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 14:04
 */
public interface CheckinUseCase
{
//	CustomerDTO updateCheckin(CustomerRequestDTO customerDto);
	
	CheckinUseCaseDTO createCustomerCheckin(CheckinUseCaseRequestDTO customerRequestDto) throws Throwable;
	
//	/**
//	 * check the current checkin of logging user. It will find the current hotel of user
//	 * @param dto
//	 * @return
//	 */
//	@Transactional
//	CheckinDTO updateOwnDtoCheckinInfo(CheckinRequestDTO dto) throws JsonError;
	
	//eu: here domainId, no just id!!!
	@Transactional
	CustomerDTO getStaffbyHotelId(HotelDomainEntityId hotelId);

	public CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, CheckinRootDomainEntity validCheckin);

}
