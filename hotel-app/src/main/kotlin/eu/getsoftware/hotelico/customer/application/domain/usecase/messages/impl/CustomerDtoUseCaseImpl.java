package eu.getsoftware.hotelico.customer.application.domain.usecase.messages.impl;

import eu.getsoftware.hotelico.chat.domain.ChatMessageView;
import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.common.utils.ControllerUtils;
import eu.getsoftware.hotelico.customer.application.domain.usecase.messages.CustomerDtoUseCase;
import eu.getsoftware.hotelico.customer.common.iEntity.ICustomerEntity;

import java.sql.Timestamp;

public class CustomerDtoUseCaseImpl implements CustomerDtoUseCase
{

	@Override
	public CustomerDTO setDtoLastMessageWithRequester(ICustomerEntity requester, CustomerDTO dto) {
		
		if(requester==null || dto==null)
		{
			if(requester==null && dto!=null)
			{
				dto.setInMyHotel(true);
			}

			return  dto;
		}

		//TODO Eugen: how to call other microservice???
		ChatMessageView lastMessage = chatService.getLastMessageByCustomerAndReceiverIds(dto.getId(), requester.getId());

		long requesterHotelId = this.getCustomerHotelId(requester.getId());

		dto.setInMyHotel(requesterHotelId > 0 && requesterHotelId == dto.getHotelId());

		if(lastMessage!=null)
		{
			Timestamp lastMessageTimestamp = lastMessage.getTimestamp();

			String time = ControllerUtils.getTimeFormatted(lastMessageTimestamp);

			String message = lastMessage.getMessage();
			message = message.length() > 11 ? message.substring(0, 10)+"...": message;

			dto.setLastMessageToMe(message + " (at " + time + ")");
			dto.setLastMessageTimeToMe(lastMessageTimestamp.getTime());
			dto.setChatWithMe(true);
		}

		return dto;
	}
}
