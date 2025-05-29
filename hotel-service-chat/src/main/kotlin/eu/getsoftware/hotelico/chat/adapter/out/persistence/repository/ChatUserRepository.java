package eu.getsoftware.hotelico.chat.adapter.out.persistence.repository;

import eu.getsoftware.hotelico.chat.adapter.out.persistence.model.ChatUserMappedEntity;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatUserRepository extends MongoRepository<ChatUserMappedEntity, Long> {
	
	ChatUserMappedEntity findByUserId(long userId);
	
	ChatUserMappedEntity findByUserDomainId(CustomerDomainEntityId userDomainId);
}
