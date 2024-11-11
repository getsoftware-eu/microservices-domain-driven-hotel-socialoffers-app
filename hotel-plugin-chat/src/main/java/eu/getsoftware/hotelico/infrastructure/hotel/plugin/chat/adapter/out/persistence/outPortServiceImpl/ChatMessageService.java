package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.repository.ChatMessageRepository;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.port.out.IChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements IChatMessageService {
	
	private final ChatMessageRepository chatMessageRepository;
	
	public void saveMessage(ChatMessageEntity message) {
		chatMessageRepository.save(message);
	}
	
	public List<ChatMessageEntity> getAllMessages() {
		return chatMessageRepository.findAll();
	}
	
	public Optional<ChatMessageEntity> getLastMessageByCustomerAndReceiverIds(CustomerDomainEntityId customerId, CustomerDomainEntityId receiverId){
		return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(customerId, receiverId);
	}
	
	public Optional<ChatMessageEntity> getLastChatMessage(CustomerDomainEntityId fromCustomerId, CustomerDomainEntityId toCustomerId)
	{
		return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(fromCustomerId, toCustomerId);
	}

	public void save(ChatMsgDTO chatMsgDTO) {
		
	}

	@Override
	public Optional<ChatMessageEntity> getLastMessageByCustomerAndReceiverIds(long customerId, long receiverId) {
		return Optional.empty();
	}

	@Override
	public Optional<ChatMessageEntity> getLastChatMessage(Long fromCustomerId, Long toCustomerId) {
		return Optional.empty();
	}
}
