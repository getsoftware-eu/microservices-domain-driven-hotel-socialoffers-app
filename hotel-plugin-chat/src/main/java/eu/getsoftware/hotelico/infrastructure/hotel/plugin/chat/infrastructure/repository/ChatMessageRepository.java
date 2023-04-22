package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.repository;
		
import org.springframework.data.mongodb.repository.MongoRepository;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.domain.model.ChatMessageEntity;

public interface ChatMessageRepository extends MongoRepository<ChatMessageEntity, Long> {
	
}
