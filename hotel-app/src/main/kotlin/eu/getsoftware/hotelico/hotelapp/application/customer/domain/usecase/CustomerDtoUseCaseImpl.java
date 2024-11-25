package eu.getsoftware.hotelico.hotelapp.application.customer.domain.usecase;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerRequestDTO;
import eu.getsoftware.hotelico.clients.api.clients.dto.usecase.CustomerRegisterRequestUseCaseDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.exception.domain.BusinessException;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.gateways.CustomerGatewayService;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.mapper.CustomerDtoMapper;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.CustomerDomainFactory;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.customDomainModelImpl.CustomerRootDomainEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.in.CustomerDtoUseCase;

public class CustomerDtoUseCaseImpl implements CustomerDtoUseCase
{
	private CustomerGatewayService customerGatewayService;
	private CustomerDtoMapper customerDtoMapper;

	public CustomerDTO registerCustomer(CustomerRegisterRequestUseCaseDTO requestCustomerDto) {

		if (customerGatewayService.findByField("name", requestCustomerDto.name()).isPresent()) {
//			return customerResponseDTOPortPresenter.prepareFailView("Customer with name " + requestCustomerDto.name() + " already exists.");
			throw new BusinessException("Customer with name " + requestCustomerDto.name() + " already exists.");
		}

		CustomerRootDomainEntity customerDomainEntity = CustomerDomainFactory.create(requestCustomerDto.name(), requestCustomerDto.email() , requestCustomerDto.password());

		if (!customerDomainEntity.isPasswordValid())
//			return customerResponseDTOPortPresenter.prepareFailView("Customer password not valid.");
			throw new BusinessException("customer password bot valid");
			
		//A3 domain is correct, we can send it to lower layer for persist
		customerGatewayService.saveToDb(customerDomainEntity);

		return customerDtoMapper.toDto(customerDomainEntity);

//        return formatModelDTOForClientView(clientResponseDTO);
	}
	
	@Override
	public CustomerDTO setDtoLastMessageWithRequester(CustomerRequestDTO requester, CustomerDTO dto) {
		
		if(requester==null || dto==null)
		{
			if(requester==null && dto!=null)
			{
//				dto.setInMyHotel(true);
			}

			return  dto;
		}

		//TODO Eugen: how to call other microservice???
//		IChatMessageView lastMessage = chatService.getLastMessageByCustomerAndReceiverIds(dto.getId(), requester.requesterId());
//
//		long requesterHotelId = this.getCustomerHotelId(requester.getId());
//
//		dto.setInMyHotel(requesterHotelId > 0 && requesterHotelId == dto.getHotelId());

//		if(lastMessage!=null)
//		{
//			Timestamp lastMessageTimestamp = lastMessage.getTimestamp();
//
//			String time = AppConfigProperties.getTimeFormatted(lastMessageTimestamp);
//
//			String message = lastMessage.getMessage();
//			message = message.length() > 11 ? message.substring(0, 10)+"...": message;
//
////			dto.setLastMessageToMe(message + " (at " + time + ")");
////			dto.setLastMessageTimeToMe(lastMessageTimestamp.getTime());
////			dto.setChatWithMe(true);
//		}

		return dto;
	}
}
