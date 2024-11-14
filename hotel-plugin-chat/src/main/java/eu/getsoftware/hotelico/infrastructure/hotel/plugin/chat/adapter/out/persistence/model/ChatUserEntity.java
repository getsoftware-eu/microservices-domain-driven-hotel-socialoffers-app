package eu.getsoftware.hotelico.infrastructure.hotel.plugin.chat.adapter.out.persistence.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter @Setter
@Document(collection = "chat_users")
public class ChatUserEntity implements Serializable
{
  private static final long serialVersionUID = -5478152926695631989L;
  
  @Id
  private long id;
  
  @Convert(converter = CustomerDomainEntityId.class)
  private CustomerDomainEntityId userDomainId;
  
  private String firstName;
  
  private String lastName;
  
  private String email;
  
  public ChatUserEntity(CustomerDomainEntityId userDomainId) 
  {
    this.userDomainId = userDomainId;
  }
}
