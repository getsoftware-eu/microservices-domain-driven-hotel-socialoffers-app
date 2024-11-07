package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.repository.ChatMessageRepository;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.port.out.IChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService implements IChatMessageService {
	
	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	public void saveMessage(ChatMessageEntity message) {
		chatMessageRepository.save(message);
	}
	
	public List<ChatMessageEntity> getAllMessages() {
		return chatMessageRepository.findAll();
	}
	
	public Optional<ChatMessageEntity> getLastMessageByCustomerAndReceiverIds(long customerId, long receiverId){
		return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(customerId, receiverId);
	}
	
	public Optional<ChatMessageEntity> getLastChatMessage(Long fromCustomerId, Long toCustomerId)
	{
		return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(fromCustomerId, toCustomerId);
	}

	public void save(ChatMsgDTO chatMsgDTO) {
		
	}
}
