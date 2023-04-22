package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.domain.model.ChatUserEntity;

public interface ChatUserRepository extends MongoRepository<ChatUserEntity, Long> {
	
	ChatUserEntity findByUserId(long userId);
}
