package chat.adapter.out.persistence.repository

import chat.adapter.out.persistence.model.ChatMessageMappedEntity
import chat.adapter.out.persistence.model.ChatUserMappedEntity
import eu.getsoftware.hotelico.clients.api.application.infrastructure.chat.dto.ChatMsgDTO
import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param

interface ChatMessageRepository : MongoRepository<ChatMessageMappedEntity?, Long?> {

    companion object {
        
        const val FIND_DTO_MESSAGES_BY_CUSTOMER_IDS: String = """
            SELECT new eu.getsoftware.hotelico.chat.infrastructure.dto.ChatMessageDTO(
                    m.name, m.title, m.composer
                ) 
            FROM ChatMessage m
            WHERE m.active = true
                AND ( 
                      (m.sender.id = :customerId AND m.receiver.id = :receiverId) 
                      OR 
                      (m.sender.id = :receiverId AND m.receiver.id = :customerId) 
                     ) 
                AND m.sender.active = TRUE 
                AND m.receiver.active = TRUE 
        """

        const val FIND_MESSAGES_BY_CUSTOMER_IDS: String = """
            SELECT m 
             FROM ChatMessage m 
             WHERE m.active = true 
                 AND ( 
                 (m.sender.id = :customerId AND m.receiver.id = :receiverId) 
                  OR 
                 (m.sender.id = :receiverId AND m.receiver.id = :customerId) 
                 ) 
                 AND m.sender.active = TRUE AND m.receiver.active = TRUE 
        """

        const val FIND_LAST_MESSAGE_BY_CUSTOMER_AND_RECEIVER_IDS: String = "SELECT m " +
                "FROM ChatMessage m " +
                "WHERE m.id = (select max(mm.id) from ChatMessage mm where " +
                "mm.active = true " +
                "AND ( " +
                "(mm.sender.id = :customerId AND mm.receiver.id = :receiverId) " +
                " OR " +
                "(mm.sender.id = :receiverId AND mm.receiver.id = :customerId) " +
                ") " +
                ")"

        const val FIND_LAST_MESSAGE_BY_CUSTOMER_ID: String = "SELECT m " +
                "FROM ChatMessage m " +
                "WHERE m.id = (select max(mm.id) from ChatMessage mm where " +
                "mm.active = true " +
                "AND ( " +
                "(mm.sender.id = :customerId) " +
                " OR " +
                "(mm.receiver.id = :customerId) " +
                ") " +
                ")"

        const val FIND_CHAT_SENDER_BY_CUSTOMER_ID: String = "SELECT distinct m.sender " +
                "FROM ChatMessage m " +
                "WHERE " +
                "m.active = true " +
                "AND ( " +
                "(m.sender.id = :customerId) " +
                " OR " +
                "(m.receiver.id = :customerId) " +
                ") " +
                "AND m.sender.active = TRUE AND m.receiver.active = TRUE "

        const val FIND_CHAT_RECEIVER_BY_CUSTOMER_ID: String = "SELECT distinct m.receiver " +
                "FROM ChatMessage m " +
                "WHERE " +
                "m.active = true " +
                "AND ( " +
                "(m.sender.id = :customerId) " +
                " OR " +
                "(m.receiver.id = :customerId) " +
                ") " +
                "AND m.sender.active = TRUE AND m.receiver.active = TRUE "

        const val FIND_NOT_READ_MESSAGE_COUNTER_BY_RECEIVER_IDS: String = "SELECT count(m) " +
                "FROM ChatMessage m " +
                "WHERE m.active = true AND m.seenByReceiver = false " +
                "AND (m.sender.id = :senderId AND m.receiver.id = :customerId) " +
                "AND m.sender.active = TRUE AND m.receiver.active = TRUE"

        const val FIND_NOT_READ_MESSAGES_BY_RECEIVER_ID: String = "SELECT m " +
                "FROM ChatMessage m " +
                "WHERE m.active = true AND m.seenByReceiver = false " +
                "AND m.receiver.id = :customerId " +
                "AND m.sender.active = TRUE "

        const val FIND_NOT_READ_MESSAGES_BY_SENDER_AND_RECEIVER_ID: String = "SELECT m " +
                "FROM ChatMessage m " +
                "WHERE m.active = true AND m.seenByReceiver = false " +
                "AND m.receiver.id = :customerId " +
                "AND m.sender.id = :senderId "

        const val FIND_NOT_READ_CHATS_BY_CUSTOMER_ID: String = "SELECT count(distinct m.sender) " +
                "FROM ChatMessage m " +
                "WHERE m.active = true AND m.seenByReceiver = false " +
                "AND m.receiver.id = :customerId " +
                "AND m.sender.active = TRUE "


        const val FIND_MESSAGE_BY_ID_AND_CUSTOMER_ID: String = "SELECT m " +
                "FROM ChatMessage m " +
                "WHERE m.active = true AND m.initId = :initId " +
                "AND m.receiver.id = :customerId"

        const val FIND_MESSAGE_BY_MESSAGE_ID: String = """
            SELECT m 
            FROM ChatMessage m 
            WHERE m.active = true AND m.initId = :initId 
        """

        const val EXIST_MESSAGE_BY_MESSAGE_ID: String = """
            SELECT count(m)>0 
            FROM ChatMessage m 
            WHERE m.active = true AND m.initId = :initId 
        """
    }
    
    /**
     * Eugen: generate DTO messagesDTO by customer ids direct from DB.
     */
    @Query(FIND_DTO_MESSAGES_BY_CUSTOMER_IDS)
    fun getMessagesDTOByCustomerIds(
        @Param("customerId") customerId: CustomerDomainEntityId,
        @Param("receiverDomainId") receiverId: Long?
    ): List<ChatMsgDTO>

    /**
     * Find messages by customer ids.
     */
    @Query(FIND_MESSAGES_BY_CUSTOMER_IDS)
    fun getMessagesByCustomerIds(
        @Param("customerId") customerId: CustomerDomainEntityId,
        @Param("receiverDomainId") receiverId: Long
    ): List<ChatMessageMappedEntity>

    @Query(FIND_LAST_MESSAGE_BY_CUSTOMER_AND_RECEIVER_IDS)
    fun getLastMessageByCustomerAndReceiverIds(
        @Param("customerId") customerId: CustomerDomainEntityId,
        @Param("receiverDomainId") receiverId: CustomerDomainEntityId
    ): ChatMessageMappedEntity?

    @Query(FIND_LAST_MESSAGE_BY_CUSTOMER_ID)
    fun getLastMessageOnlyByCustomerId(@Param("customerId") customerId: CustomerDomainEntityId): List<ChatMessageMappedEntity>

    @Query(FIND_CHAT_SENDER_BY_CUSTOMER_ID)
    fun getChatSendersByCustomerId(@Param("customerId") customerId: CustomerDomainEntityId): List<ChatUserMappedEntity>

    @Query(FIND_CHAT_RECEIVER_BY_CUSTOMER_ID)
    fun getChatReceiversByCustomerId(@Param("customerId") customerId: CustomerDomainEntityId): List<ChatUserMappedEntity>

    @Query(FIND_NOT_READ_MESSAGE_COUNTER_BY_RECEIVER_IDS)
    fun getNotReadMessageCountBySenderId(
        @Param("customerId") customerId: CustomerDomainEntityId,
        @Param("senderDomainId") senderId: Int
    ): Int?

    @Query(FIND_NOT_READ_CHATS_BY_CUSTOMER_ID)
    fun getNotReadSenderByCustomerId(@Param("customerId") customerId: CustomerDomainEntityId): Int?

    @Query(FIND_NOT_READ_MESSAGES_BY_RECEIVER_ID)
    fun getUnreadChatMessagesForCustomer(@Param("customerId") customerId: CustomerDomainEntityId): List<ChatMessageMappedEntity>

    @Query(FIND_NOT_READ_MESSAGES_BY_SENDER_AND_RECEIVER_ID)
    fun getUnreadChatMessagesForCustomerBySender(
        @Param("customerId") customerId: CustomerDomainEntityId,
        @Param("senderDomainId") senderId: Long
    ): List<ChatMessageMappedEntity?>?

    @Query(FIND_MESSAGE_BY_ID_AND_CUSTOMER_ID)
    fun getMessageByCustomerAndInitId(
        @Param("customerId") customerId: CustomerDomainEntityId,
        @Param("initId") initId: Long
    ): List<ChatMessageMappedEntity?>?

    @Query(FIND_MESSAGE_BY_MESSAGE_ID)
    fun getMessageByInitId(@Param("initId") initId: Long): List<ChatMessageMappedEntity>

    @Query(EXIST_MESSAGE_BY_MESSAGE_ID)
    fun existMessageWithInitId(@Param("initId") initId: Long): Boolean

    
}
