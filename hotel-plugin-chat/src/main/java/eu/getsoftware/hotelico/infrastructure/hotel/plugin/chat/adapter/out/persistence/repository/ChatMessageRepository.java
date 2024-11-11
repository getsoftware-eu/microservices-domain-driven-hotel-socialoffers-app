package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.repository;

import eu.getsoftware.hotelico.clients.api.clients.infrastructure.chat.dto.ChatMsgDTO;
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatMessageEntity;
import eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model.ChatUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessageEntity, Long> {
	
	public final static String FIND_DTO_MESSAGES_BY_CUSTOMER_IDS = "SELECT new eu.getsoftware.hotelico.chat.infrastructure.dto.ChatMessageDTO(m.name, m.title, m.composer) " +
			"FROM ChatMessage m " +
			"WHERE m.active = true " +
			"AND ( " +
			"(m.sender.id = :customerId AND m.receiver.id = :receiverId) " +
			" OR " +
			"(m.sender.id = :receiverId AND m.receiver.id = :customerId) " +
			") "+
			"AND m.sender.active = TRUE AND m.receiver.active = TRUE ";
	
	public final static String FIND_MESSAGES_BY_CUSTOMER_IDS = "SELECT m " +
			"FROM ChatMessage m " +
			"WHERE m.active = true " +
			"AND ( " +
			"(m.sender.id = :customerId AND m.receiver.id = :receiverId) " +
			" OR " +
			"(m.sender.id = :receiverId AND m.receiver.id = :customerId) " +
			") "+
			"AND m.sender.active = TRUE AND m.receiver.active = TRUE ";
	
	public final static String FIND_LAST_MESSAGE_BY_CUSTOMER_AND_RECEIVER_IDS = "SELECT m " +
			"FROM ChatMessage m " +
			"WHERE m.id = (select max(mm.id) from ChatMessage mm where "+
			"mm.active = true " +
			"AND ( " +
			"(mm.sender.id = :customerId AND mm.receiver.id = :receiverId) " +
			" OR " +
			"(mm.sender.id = :receiverId AND mm.receiver.id = :customerId) " +
			") "+
			")";
	
	public final static String FIND_LAST_MESSAGE_BY_CUSTOMER_ID = "SELECT m " +
			"FROM ChatMessage m " +
			"WHERE m.id = (select max(mm.id) from ChatMessage mm where "+
			"mm.active = true " +
			"AND ( " +
			"(mm.sender.id = :customerId) " +
			" OR " +
			"(mm.receiver.id = :customerId) " +
			") "+
			")";
	
	public final static String FIND_CHAT_SENDER_BY_CUSTOMER_ID = "SELECT distinct m.sender " +
			"FROM ChatMessage m " +
			"WHERE " +
			"m.active = true " +
			"AND ( " +
			"(m.sender.id = :customerId) " +
			" OR " +
			"(m.receiver.id = :customerId) " +
			") "+
			"AND m.sender.active = TRUE AND m.receiver.active = TRUE ";
	
	public final static String FIND_CHAT_RECEIVER_BY_CUSTOMER_ID = "SELECT distinct m.receiver " +
			"FROM ChatMessage m " +
			"WHERE " +
			"m.active = true " +
			"AND ( " +
			"(m.sender.id = :customerId) " +
			" OR " +
			"(m.receiver.id = :customerId) " +
			") "+
			"AND m.sender.active = TRUE AND m.receiver.active = TRUE ";
	
	public final static String FIND_NOT_READ_MESSAGE_COUNTER_BY_RECEIVER_IDS = "SELECT count(m) " +
			"FROM ChatMessage m " +
			"WHERE m.active = true AND m.seenByReceiver = false " +
			"AND (m.sender.id = :senderId AND m.receiver.id = :customerId) "+
			"AND m.sender.active = TRUE AND m.receiver.active = TRUE";
	;
	
	public final static String FIND_NOT_READ_MESSAGES_BY_RECEIVER_ID = "SELECT m " +
			"FROM ChatMessage m " +
			"WHERE m.active = true AND m.seenByReceiver = false " +
			"AND m.receiver.id = :customerId " +
			"AND m.sender.active = TRUE ";
	
	public final static String FIND_NOT_READ_MESSAGES_BY_SENDER_AND_RECEIVER_ID = "SELECT m " +
			"FROM ChatMessage m " +
			"WHERE m.active = true AND m.seenByReceiver = false " +
			"AND m.receiver.id = :customerId " +
			"AND m.sender.id = :senderId ";
	
	public final static String FIND_NOT_READ_CHATS_BY_CUSTOMER_ID = "SELECT count(distinct m.sender) " +
			"FROM ChatMessage m " +
			"WHERE m.active = true AND m.seenByReceiver = false " +
			"AND m.receiver.id = :customerId "+
			"AND m.sender.active = TRUE ";
	
	
	public final static String FIND_MESSAGE_BY_ID_AND_CUSTOMER_ID = "SELECT m " +
			"FROM ChatMessage m " +
			"WHERE m.active = true AND m.initId = :initId " +
			"AND m.receiver.id = :customerId";
	
	public final static String FIND_MESSAGE_BY_MESSAGE_ID = "SELECT m " +
			"FROM ChatMessage m " +
			"WHERE m.active = true AND m.initId = :initId ";
	
	public final static String EXIST_MESSAGE_BY_MESSAGE_ID = "SELECT count(m)>0 " +
			"FROM ChatMessage m " +
			"WHERE m.active = true AND m.initId = :initId ";
	
	
	/**
	 * Eugen: generate DTO messagesDTO by customer ids direct from DB.
	 */
	@Query(FIND_DTO_MESSAGES_BY_CUSTOMER_IDS)
	public List<ChatMsgDTO>getMessagesDTOByCustomerIds(@Param("customerId") CustomerDomainEntityId customerId, @Param("receiverDomainId") Long receiverId);
	
	/**
	 * Find messages by customer ids.
	 */
	@Query(FIND_MESSAGES_BY_CUSTOMER_IDS)
	public List<ChatMessageEntity> getMessagesByCustomerIds(@Param("customerId") CustomerDomainEntityId customerId, @Param("receiverDomainId") Long receiverId);
	
	@Query(FIND_LAST_MESSAGE_BY_CUSTOMER_AND_RECEIVER_IDS)
	public Optional<ChatMessageEntity> getLastMessageByCustomerAndReceiverIds(@Param("customerId") CustomerDomainEntityId customerId, @Param("receiverDomainId") CustomerDomainEntityId receiverId);
	
	@Query(FIND_LAST_MESSAGE_BY_CUSTOMER_ID)
	public List<ChatMessageEntity> getLastMessageOnlyByCustomerId(@Param("customerId") CustomerDomainEntityId customerId);
	
	@Query(FIND_CHAT_SENDER_BY_CUSTOMER_ID)
	public List<ChatUserEntity> getChatSendersByCustomerId(@Param("customerId") CustomerDomainEntityId customerId);
	
	@Query(FIND_CHAT_RECEIVER_BY_CUSTOMER_ID)
	public List<ChatUserEntity> getChatReceiversByCustomerId(@Param("customerId") CustomerDomainEntityId customerId);
	
	@Query(FIND_NOT_READ_MESSAGE_COUNTER_BY_RECEIVER_IDS)
	public Integer getNotReadMessageCountBySenderId(@Param("customerId") CustomerDomainEntityId customerId, @Param("senderDomainId") Integer senderId);
	
	@Query(FIND_NOT_READ_CHATS_BY_CUSTOMER_ID)
	public Integer getNotReadSenderByCustomerId(@Param("customerId") CustomerDomainEntityId customerId);
	
	@Query(FIND_NOT_READ_MESSAGES_BY_RECEIVER_ID)
	List<ChatMessageEntity> getUnreadChatMessagesForCustomer(@Param("customerId") CustomerDomainEntityId customerId);
	
	@Query(FIND_NOT_READ_MESSAGES_BY_SENDER_AND_RECEIVER_ID)
	List<ChatMessageEntity> getUnreadChatMessagesForCustomerBySender(@Param("customerId") CustomerDomainEntityId customerId, @Param("senderDomainId") Long senderId);
	
	@Query(FIND_MESSAGE_BY_ID_AND_CUSTOMER_ID)
	List<ChatMessageEntity> getMessageByCustomerAndInitId(@Param("customerId") CustomerDomainEntityId customerId, @Param("initId") Long initId);
	
	@Query(FIND_MESSAGE_BY_MESSAGE_ID)
	List<ChatMessageEntity> getMessageByInitId(@Param("initId") Long initId);
	
	@Query(EXIST_MESSAGE_BY_MESSAGE_ID)
	boolean existMessageWithInitId(@Param("initId") Long initId);
}
