package eu.getsoftware.hotelico.hotel.chat.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import eu.getsoftware.hotelico.hotel.chat.utils.HibernateUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "chat_msg")
public class ChatMsgEntity implements Serializable
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
  private Timestamp timestamp = new Timestamp(new Date().getTime());

  @ManyToOne
  @JoinColumn(name="senderId")
  private ChatCustomerEntity sender;
  
  @Column(name = "seenByReceiver", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
  private boolean seenByReceiver = false;  
	
  @Column(name = "delieveredToReceiver", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
  private boolean delieveredToReceiver = false;
  
  @ManyToOne
  @JoinColumn(name="receiverId")
  private ChatCustomerEntity receiver;

  @Column
  private String specialChatContent;
  
  public ChatMsgEntity() 
  {
    this.timestamp = new Timestamp(new Date().getTime());
  }
  
  public ChatMsgEntity(String message, ChatCustomerEntity sender, ChatCustomerEntity receiver) {
    
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
  
    ChatMsgEntity that = (ChatMsgEntity) o;

    if (id != that.id)
    {
      return false;
    }

    if (that.initId != this.initId)
    {
      return false;
    }

    if (sender != null ? !sender.equals(that.sender) : that.sender != null)
    {
      return false;
    }
    return !(receiver != null ? !receiver.equals(that.receiver) : that.receiver != null);

  }

  @Override
  public int hashCode()
  {
    int result = (int)id;
    result = 31 * result + (active ? 1 : 0);
    result = 31 * result + (message != null ? message.hashCode() : 0);
    result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
    result = 31 * result + (sender != null ? sender.hashCode() : 0);
    result = 31 * result + (seenByReceiver ? 1 : 0);
    result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
    return result;
  }
}
