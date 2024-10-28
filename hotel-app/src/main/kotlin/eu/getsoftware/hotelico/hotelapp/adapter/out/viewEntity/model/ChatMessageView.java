package eu.getsoftware.hotelico.hotelapp.adapter.out.viewEntity.model;

import eu.getsoftware.hotelico.clients.api.clients.domain.chat.IChatMessageView;
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerRootEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
@Entity
@Getter @Setter
@Table(name = "chat_message", schema = "chat")
public class ChatMessageView implements IChatMessageView, Serializable
{
  private static final long serialVersionUID = -5478152926665631989L;
  
  @Id
  @Setter(AccessLevel.PROTECTED)
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;

  @Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
  private boolean active = true;
  
  @Column
  private String message;

  @Column(name = "initId", columnDefinition = "BIGINT(20) DEFAULT 0")
  private long initId;

  @Column
  private Timestamp timestamp;

//  @ManyToOne
//  @JoinColumn(name="senderId")
//  private CustomerRootEntity sender;
  @Column
  private long senderId;
  
  @Column(name = "seenByReceiver", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
  private boolean seenByReceiver = false;  
	
  @Column(name = "delieveredToReceiver", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
  private boolean delieveredToReceiver = false;
  
//  @ManyToOne
//  @JoinColumn(name="receiverId")
//  private CustomerRootEntity receiver;
  @Column
  private long receiverId;

  @Column
  private String specialChatContent;
  
  public ChatMessageView() 
  {
    this.timestamp = new Timestamp(new Date().getTime());
  }
  
  public ChatMessageView(String message, CustomerRootEntity sender, CustomerRootEntity receiver) {
    
    this();
    
    this.message = message;
    this.senderId = sender.getId();
    this.receiverId = receiver.getId();
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

    ChatMessageView that = (ChatMessageView) o;

    if (id != that.id)
    {
      return false;
    }

    if (that.initId != this.initId)
    {
      return false;
    }

    if (!Objects.equals(senderId, that.senderId))
    {
      return false;
    }
    return !(!Objects.equals(receiverId, that.receiverId));

  }

  @Override
  public int hashCode()
  {
    int result = (int)id;
    result = 31 * result + (active ? 1 : 0);
    result = 31 * result + (message != null ? message.hashCode() : 0);
    result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
    result = 31 * result + (seenByReceiver ? 1 : 0);
    return result;
  }
}
