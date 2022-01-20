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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Table(name = "hotel_wallpost")
public class HotelWallPost implements Serializable
{


	private static final long serialVersionUID = -5478152926665631989L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;

	@Column
	private String message;	
	
	@Column
	private String specialWallContent;

	@Column(name = "initId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long initId;	
	
	@Column
	private Integer replyOnWallPostId;

	@Column
	private Timestamp timestamp;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "validUntil", nullable = true, length = 10)
	private Date validUntil;
	
	@ManyToOne
	@JoinColumn(name="senderId")
	private Customer sender;

	@ManyToOne
	@JoinColumn(name="hotelId")
	private Hotel hotel;

	public HotelWallPost()
	{
		this.timestamp = new Timestamp(new Date().getTime());

	}

	public HotelWallPost(String message, Customer sender, Hotel hotel) {

		this();

		this.message = message;
		this.sender = sender;
		this.hotel = hotel;
	}
	

	public long getId()
	{
		return id;
	}

	public String getMessage()
	{
		return message;
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

	public Hotel getHotel()
	{
		return hotel;
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

//	public void setReceiver(Hotel hotel)
//	{
//		this.hotel = hotel;
//	}

	public void setId(int id)
	{
//		this.id = id;
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

	public void setValidUntil(Date validUntil)
	{
		this.validUntil = validUntil;
	}

	public void setHotel(Hotel hotel)
	{
		this.hotel = hotel;
	}

	public Date getValidUntil()
	{
		return validUntil;
	}

	public Integer getReplyOnWallPostId()
	{
		return replyOnWallPostId;
	}

	public void setReplyOnWallPostId(Integer replyOnWallPostId)
	{
		this.replyOnWallPostId = replyOnWallPostId;
	}
	
	public String getSpecialWallContent()
	{
		return specialWallContent;
	}
	
	public void setSpecialWallContent(String specialType)
	{
		this.specialWallContent = specialType;
	}
}

