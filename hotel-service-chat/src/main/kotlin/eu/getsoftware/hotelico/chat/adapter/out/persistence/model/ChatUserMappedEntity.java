package eu.getsoftware.hotelico.chat.adapter.out.persistence.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.CustomerDomainEntityId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter @Setter
@Document(collection = "chat_users")
public class ChatUserMappedEntity implements Serializable
{
  private static final long serialVersionUID = -5478152926695631989L;
  
  @Id
  private long id;

  @Getter
//  @Convert(converter = CustomerDomainEntityId.class)
  //@Embedded @Column(unique = true, nullable = false)
  private CustomerDomainEntityId userDomainId;

  public ChatUserMappedEntity(CustomerDomainEntityId userDomainId){
    this.userDomainId = userDomainId;
  }
  
  private String firstName;
  
  private String lastName;
  
  private String email;
  
  public CustomerDomainEntityId getUserDomainId() {
    return userDomainId;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return firstName;
  }
}
