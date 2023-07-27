package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.service;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.domain.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
	
	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	public void saveMessage(ChatMessageEntity message) {
		chatMessageRepository.save(message);
	}
	
	public List<ChatMessageEntity> getAllMessages() {
		return chatMessageRepository.findAll();
	}
	
	public ChatMessageEntity getLastMessageByCustomerAndReceiverIds(long customerId, long receiverId){
		return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(customerId, receiverId);
	}
	
	public Optional<ChatMessageEntity> getLastChatMessage(Long fromCustomerId, Long toCustomerId)
	{
		return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(fromCustomerId, toCustomerId);
	}
}
