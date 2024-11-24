package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;


@Getter @Setter
@RequiredArgsConstructor
@Document(collection = "chat_messages") //Every bounded context stores its entities in its own DB schema
public class ChatMessageMappedEntity implements Serializable
{
  private static final long serialVersionUID = -5478152926665631989L;
  
  @Id
  private long id;
  
  private final String message;

  private long initId;
  
  private final CustomerDomainEntityId senderId;
  private final CustomerDomainEntityId receiverId;
  
  private boolean seenByReceiver = false;
  
  private boolean delieveredToReceiver = false;
  
  private Timestamp timestamp;

  private String specialChatContent;
  
}
