package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.controller;

import eu.getsoftware.hotelico.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.infrastructure.hotel.dto.CustomerDTO;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.domain.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.domain.model.ChatUserEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.repository.ChatMessageRepository;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.repository.ChatUserRepository;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.service.ChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/chat")
public class ChatMSCommunicationController {
	
	private final ChatMessageRepository chatMessageRepository;	
	private final ChatUserRepository chatUserRepository;
	private final ChatService chatService;
	
	public ChatMSCommunicationController(ChatMessageRepository chatMessageRepository, ChatUserRepository chatUserRepository, ChatService chatService)
	{
		this.chatMessageRepository = chatMessageRepository;
		this.chatUserRepository = chatUserRepository;
		this.chatService = chatService;
	}
	
	@PostMapping("/message")
	public ChatMsgDTO postMessage(@RequestBody ChatMsgDTO msgDTO) {
		
		ChatMessageEntity createdEntity = chatService.createChatMessageFromDTO(msgDTO);
		return chatService.convertToDTO(createdEntity);
	}
	
	
	@PostMapping("/customer")
	public CustomerDTO updateUser(@RequestBody CustomerDTO customerDTO) {
		
		ChatUserEntity updateEntity = chatService.updateCustomerFromDTO(customerDTO);
		return chatService.convertToDTO(updateEntity);
	}
}
