package eu.getsoftware.hotelico.hotelapp.application.customer.domain.usecase.messages;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotelapp.application.chat.domain.model.IChatMessageView;
import eu.getsoftware.hotelico.hotelapp.application.customer.common.iEntity.ICustomerEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.in.CustomerDtoUseCase;

import java.sql.Timestamp;

public class CustomerDtoUseCaseImpl implements CustomerDtoUseCase
{

//	@Autowired
//	private IChatService chatService;
	
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
		IChatMessageView lastMessage = chatService.getLastMessageByCustomerAndReceiverIds(dto.getId(), requester.getId());

		long requesterHotelId = this.getCustomerHotelId(requester.getId());

		dto.setInMyHotel(requesterHotelId > 0 && requesterHotelId == dto.getHotelId());

		if(lastMessage!=null)
		{
			Timestamp lastMessageTimestamp = lastMessage.getTimestamp();

			String time = AppConfigProperties.getTimeFormatted(lastMessageTimestamp);

			String message = lastMessage.getMessage();
			message = message.length() > 11 ? message.substring(0, 10)+"...": message;

			dto.setLastMessageToMe(message + " (at " + time + ")");
			dto.setLastMessageTimeToMe(lastMessageTimestamp.getTime());
			dto.setChatWithMe(true);
		}

		return dto;
	}
}
