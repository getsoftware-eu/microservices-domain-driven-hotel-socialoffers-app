package eu.getsoftware.hotelico.service.booking.adapter.out.persistence.hotel.model;

import eu.getsoftware.hotelico.clients.common.domain.domainIDs.WallPostDomainEntityId;
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.service.booking.adapter.out.persistence.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.service.booking.application.hotel.domain.model.IHotelWallPost;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "hotel_wallpost", schema = "hotel")
public class HotelWallPost implements IHotelWallPost, Serializable
{
	private static final long serialVersionUID = -5478152926665631989L;

	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@Embedded @Column
//	@Convert(converter = WallPostDomainEntityIdConverter.class)
	private WallPostDomainEntityId domainEntityId;

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
	private LocalDate validUntil;
	
	@ManyToOne
	@JoinColumn(name="senderDomainId")
	private CustomerDBEntity sender;

	@ManyToOne
	@JoinColumn(name="hotelId")
	private HotelDBEntity hotel;

	public HotelWallPost()
	{
		this.timestamp = new Timestamp(System.currentTimeMillis());

	}

	public HotelWallPost(String message, CustomerDBEntity sender, HotelDBEntity hotel) {

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

