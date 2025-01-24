package chat.adapter.out.persistence.outPortServiceImpl;

import chat.adapter.out.persistence.model.ChatMessageMappedEntity;
import chat.adapter.out.persistence.repository.ChatMessageRepository;
import chat.application.port.out.IChatMessageService;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService implements IChatMessageService {
	
	private final ChatMessageRepository chatMessageRepository;
	
	public void saveMessage(ChatMessageMappedEntity message) {
		chatMessageRepository.save(message);
	}
	
	public List<ChatMessageMappedEntity> getAllMessages() {
		return chatMessageRepository.findAll();
	}
	
	public Optional<ChatMessageMappedEntity> getLastMessageByCustomerAndReceiverIds(CustomerDomainEntityId customerId, CustomerDomainEntityId receiverId){
		return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(customerId, receiverId);
	}
	
	public Optional<ChatMessageMappedEntity> getLastChatMessage(CustomerDomainEntityId fromCustomerId, CustomerDomainEntityId toCustomerId)
	{
		return chatMessageRepository.getLastMessageByCustomerAndReceiverIds(fromCustomerId, toCustomerId);
	}

	public void save(ChatMsgDTO chatMsgDTO) {
		
	}

	@Override
	public Optional<ChatMessageMappedEntity> getLastMessageByCustomerAndReceiverIds(long customerId, long receiverId) {
		return Optional.empty();
	}

	@Override
	public Optional<ChatMessageMappedEntity> getLastChatMessage(Long fromCustomerId, Long toCustomerId) {
		return Optional.empty();
	}
}
