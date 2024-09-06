package eu.getsoftware.hotelico.chat.domain;

import eu.getsoftware.hotelico.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.customer.adapter.out.persistence.model.CustomerRootEntity;
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
@Table(name = "chat_message")
public class ChatMessageView implements Serializable
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

  @ManyToOne
  @JoinColumn(name="senderId")
  private CustomerRootEntity sender;
  
  @Column(name = "seenByReceiver", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
  private boolean seenByReceiver = false;  
	
  @Column(name = "delieveredToReceiver", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
  private boolean delieveredToReceiver = false;
  
  @ManyToOne
  @JoinColumn(name="receiverId")
  private CustomerRootEntity receiver;

  @Column
  private String specialChatContent;
  
  public ChatMessageView() 
  {
    this.timestamp = new Timestamp(new Date().getTime());
  }
  
  public ChatMessageView(String message, CustomerRootEntity sender, CustomerRootEntity receiver) {
    
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

    ChatMessageView that = (ChatMessageView) o;

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

  @Override
  public int hashCode()
  {
    int result = (int)id;
    result = 31 * result + (active ? 1 : 0);
    result = 31 * result + (message != null ? message.hashCode() : 0);
    result = 31 * result + (sender != null ? sender.hashCode() : 0);
    result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
    result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
    result = 31 * result + (seenByReceiver ? 1 : 0);
    return result;
  }
}
