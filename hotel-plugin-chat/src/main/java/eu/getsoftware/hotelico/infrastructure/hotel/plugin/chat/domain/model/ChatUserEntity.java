package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.domain.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document(collection = "chat_users")
public class ChatUserEntity implements Serializable
{
  private static final long serialVersionUID = -5478152926695631989L;
  
  @Id
  private long id;
  
  private long userId;
  
  private String firstName;
  
  private String lastName;
  
  private String email;
  
  public ChatUserEntity(long userId) 
  {
    this.userId = userId;
  }
}
