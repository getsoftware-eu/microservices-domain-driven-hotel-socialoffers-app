package eu.getsoftware.hotelico.service.booking.application.checkin.port.in.usecase;

import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CheckinUseCaseRequestDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.application.dto.entity.HotelDTO;
import eu.getsoftware.hotelico.service.booking.application.checkin.domain.CheckinRootDomainEntity;

/**
 * 
 * Only Commands идут через UseCase. UseCase - содержит бизнес-логику и взаимодействует с репозиториями.
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

	public CustomerDTO updateOwnDtoCheckinInfo(CustomerDTO dto, CheckinRootDomainEntity validCheckin);

	CustomerDTO updateCheckin(CustomerDTO sessionCustomer);

	void save(CheckinRootDomainEntity CheckinRootDomainEntity);

	CheckinRootDomainEntity createCheckinFromDto(CheckinUseCaseRequestDTO customerRequestDto);

	CheckinRootDomainEntity createCheckin(CustomerDTO customer, HotelDTO hotel, boolean isFullCheckin);
}
