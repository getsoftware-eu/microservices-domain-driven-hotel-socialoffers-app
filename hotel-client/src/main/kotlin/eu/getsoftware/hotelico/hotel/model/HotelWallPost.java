package eu.getsoftware.hotelico.hotel.model;

import eu.getsoftware.hotelico.hotel.utils.HibernateUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

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
@Getter @Setter
@Table(name = "hotel_wallpost")
public class HotelWallPost implements Serializable
{
	private static final long serialVersionUID = -5478152926665631989L;

	@Id
	@Setter(AccessLevel.PROTECTED)
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
}

