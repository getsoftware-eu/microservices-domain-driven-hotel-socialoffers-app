package chat.adapter.in.web.controller;

import chat.adapter.out.persistence.model.ChatMessageMappedEntity;
import chat.adapter.out.persistence.model.ChatUserMappedEntity;
import chat.adapter.out.persistence.repository.ChatMessageRepository;
import chat.adapter.out.persistence.repository.ChatUserRepository;
import chat.application.port.out.ChatService;
import eu.getsoftware.hotelico.clients.api.adapter.in.web.controller.IChatApiController;
import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;
import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/chat")
@RequiredArgsConstructor
public class ChatMSCommunicationController implements IChatApiController {
	
	private final ChatMessageRepository chatMessageRepository;	
	private final ChatUserRepository chatUserRepository;
	private final ChatService chatService;
	
//	@PostMapping("/message")  in interface
	@Override public ChatMsgDTO postMessage(@RequestBody ChatMsgDTO msgDTO) {
		
		ChatMessageMappedEntity createdEntity = chatService.createChatMessageFromDTO(msgDTO);
		return chatService.convertToDTO(createdEntity);
	}
	
	
//	@PostMapping("/customer") in interface
	public CustomerDTO updateUser(@RequestBody CustomerDTO customerDTO) {
		
		ChatUserMappedEntity updateEntity = chatService.updateCustomerFromDTO(customerDTO);
		return chatService.convertToDTO(updateEntity);
	}
}
