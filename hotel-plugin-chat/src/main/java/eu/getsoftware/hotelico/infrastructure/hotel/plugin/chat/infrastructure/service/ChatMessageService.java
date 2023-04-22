package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.domain.model.ChatMessageEntity;

@Service
public class MessageService {
	
	@Autowired
	private ChatMessageRepository messageRepository;
	
	public void saveMessage(ChatMessageEntity message) {
		messageRepository.save(message);
	}
	
	public List<ChatMessageEntity> getAllMessages() {
		return messageRepository.findAll();
	}
}
