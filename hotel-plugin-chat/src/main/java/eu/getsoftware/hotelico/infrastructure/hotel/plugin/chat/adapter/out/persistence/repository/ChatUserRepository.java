package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.repository;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.application.model.ChatUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatUserRepository extends MongoRepository<ChatUserEntity, Long> {
	
	ChatUserEntity findByUserId(long userId);
}
