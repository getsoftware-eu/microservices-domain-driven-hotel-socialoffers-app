package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;


@Getter @Setter
@Document(collection = "chat_messages") //Every bounded context stores its entities in its own DB schema
public class ChatMessageEntity implements Serializable
{
  private static final long serialVersionUID = -5478152926665631989L;
  
  @Id
  private long id;
  
  private String message;

  private long initId;
  
  private ChatUserEntity sender;
  private ChatUserEntity receiver;
  
  private boolean seenByReceiver = false;
  
  private boolean delieveredToReceiver = false;
  
  private Timestamp timestamp;

  private String specialChatContent;
  
  public ChatMessageEntity() 
  {
    this.timestamp = new Timestamp(new Date().getTime());
  }
  
  public ChatMessageEntity(String message, ChatUserEntity sender, ChatUserEntity receiver) {
    
    this();
    
    this.message = message;
    this.sender = sender;
    this.receiver = receiver;
  }
  
  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }

    ChatMessageEntity that = (ChatMessageEntity) o;

    if (id != that.id)
    {
      return false;
    }

    if (that.initId != this.initId)
    {
      return false;
    }

    if (!Objects.equals(sender, that.sender))
    {
      return false;
    }
    return !(!Objects.equals(receiver, that.receiver));

  }

  
}
