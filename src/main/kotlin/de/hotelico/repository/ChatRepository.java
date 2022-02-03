package de.hotelico.repository;

import de.hotelico.model.ChatMessage;
import de.hotelico.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

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
	 * Find messages by customer ids.
	 */
	@Query(FIND_MESSAGES_BY_CUSTOMER_IDS)
	public List<ChatMessage> getMessagesByCustomerIds(@Param("customerId") Long customerId, @Param("receiverId") Long receiverId);
	
	@Query(FIND_LAST_MESSAGE_BY_CUSTOMER_AND_RECEIVER_IDS)
	public ChatMessage getLastMessageByCustomerAndReceiverIds(@Param("customerId") Long customerId, @Param("receiverId") Long receiverId);
			
	@Query(FIND_LAST_MESSAGE_BY_CUSTOMER_ID)
	public List<ChatMessage> getLastMessageOnlyByCustomerId(@Param("customerId") Long customerId);	
	
	@Query(FIND_CHAT_SENDER_BY_CUSTOMER_ID)
	public List<Customer> getChatSendersByCustomerId(@Param("customerId") Long customerId);	
	
	@Query(FIND_CHAT_RECEIVER_BY_CUSTOMER_ID)
	public List<Customer> getChatReceiversByCustomerId(@Param("customerId") Long customerId);

	@Query(FIND_NOT_READ_MESSAGE_COUNTER_BY_RECEIVER_IDS)
	public Integer getNotReadMessageCountBySenderId(@Param("customerId") Long customerId, @Param("senderId") Integer senderId);
	
	@Query(FIND_NOT_READ_CHATS_BY_CUSTOMER_ID)
	public Integer getNotReadSenderByCustomerId(@Param("customerId") Long customerId);

	@Query(FIND_NOT_READ_MESSAGES_BY_RECEIVER_ID)
	List<ChatMessage> getUnreadChatMessagesForCustomer(@Param("customerId") Long customerId);
	
	@Query(FIND_NOT_READ_MESSAGES_BY_SENDER_AND_RECEIVER_ID)
	List<ChatMessage> getUnreadChatMessagesForCustomerBySender(@Param("customerId") Long customerId, @Param("senderId") Long senderId);

	@Query(FIND_MESSAGE_BY_ID_AND_CUSTOMER_ID)
	List<ChatMessage> getMessageByCustomerAndInitId(@Param("customerId") Long customerId, @Param("initId") Long initId);
	
	@Query(FIND_MESSAGE_BY_MESSAGE_ID)
	List<ChatMessage> getMessageByInitId(@Param("initId") Long initId);
	
	@Query(EXIST_MESSAGE_BY_MESSAGE_ID)
	boolean existMessageWithInitId(@Param("initId") Long initId);
}
