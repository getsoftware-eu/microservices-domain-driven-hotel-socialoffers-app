package de.hotelico.model;

import de.hotelico.utils.HibernateUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
@Entity
@Table(name = "chat_message")
public class ChatMessage implements Serializable
{

  private static final long serialVersionUID = -5478152926665631989L;
  
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
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
  private Customer sender;
  
  @Column(name = "seenByReceiver", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
  private boolean seenByReceiver = false;  
	
  @Column(name = "delieveredToReceiver", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
  private boolean delieveredToReceiver = false;
  
  @ManyToOne
  @JoinColumn(name="receiverId")
  private Customer receiver;

  @Column
  private String specialChatContent;
  
  public ChatMessage() 
  {
    this.timestamp = new Timestamp(new Date().getTime());

  }
  
  public ChatMessage(String message, Customer sender, Customer receiver) {
    
    this();
    
    this.message = message;
    this.sender = sender;
    this.receiver = receiver;
  }

  public long getId()
  {
    return id;
  }

  public String getMessage()
  {
    return message;
  }

  public void setSeenByReceiver(boolean seenByReceiver)
  {
    this.seenByReceiver = seenByReceiver;
  }

  public boolean isSeenByReceiver()
  {
    return seenByReceiver;
  }

  public long getInitId()
  {
    return initId;
  }

  public Timestamp getTimestamp()
  {
    return timestamp;
  }

  public Customer getSender()
  {
    return sender;
  }

  public Customer getReceiver()
  {
    return receiver;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public void setInitId(long initId)
  {
    this.initId = initId;
  }

  public void setTimestamp(Timestamp timestamp)
  {
    this.timestamp = timestamp;
  }

  public void setSender(Customer sender)
  {
    this.sender = sender;
  }

  public void setReceiver(Customer receiver)
  {
    this.receiver = receiver;
  }

  public static long getSerialVersionUID()
  {
    return serialVersionUID;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  public boolean isActive()
  {
    return active;
  }
	
	public boolean isDelieveredToReceiver()
	{
		return delieveredToReceiver;
	}
	
	public void setDelieveredToReceiver(boolean delieveredToReceiver)
	{
		this.delieveredToReceiver = delieveredToReceiver;
	}

  public String getSpecialChatContent()
  {
    return specialChatContent;
  }

  public void setSpecialChatContent(String specialChatContent)
  {
    this.specialChatContent = specialChatContent;
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

    ChatMessage that = (ChatMessage) o;

    if (id != that.id)
    {
      return false;
    }
//    if (active != that.active)
//    {
//      return false;
//    }
   
//    if (message != null ? !message.equals(that.message) : that.message != null)
//    {
//      return false;
//    }
    if (that.initId != this.initId)
    {
      return false;
    }
//    if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null)
//    {
//      return false;
//    }
    if (sender != null ? !sender.equals(that.sender) : that.sender != null)
    {
      return false;
    }
    return !(receiver != null ? !receiver.equals(that.receiver) : that.receiver != null);

  }

  public void setId(int id)
  {
//    this.id = id;
  }

  @Override
  public int hashCode()
  {
    int result = (int)id;
    result = 31 * result + (active ? 1 : 0);
    result = 31 * result + (message != null ? message.hashCode() : 0);
//    result = 31 * result + (initId != null ? initId.hashCode() : 0);
    result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
    result = 31 * result + (sender != null ? sender.hashCode() : 0);
    result = 31 * result + (seenByReceiver ? 1 : 0);
    result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
    return result;
  }
}
