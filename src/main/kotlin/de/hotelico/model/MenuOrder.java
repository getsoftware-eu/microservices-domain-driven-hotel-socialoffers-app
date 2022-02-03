package de.hotelico.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.hotelico.utils.DealStatus;
import de.hotelico.utils.HibernateUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "menu_order")
public class MenuOrder implements Serializable
{

	private static final long serialVersionUID = -3552760230944489778L;
	
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@Column(name = "initId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long initId;
	
	/**
	 * messageId -> creationTime
	 * consistencyId -> last update time
	 */
	@Column(name = "consistencyId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long consistencyId;

	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;	
	
	@Column(name = "orderInRoom", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean orderInRoom = true;
	
	@ManyToOne
	@JoinColumn(name="hotelId")
	private Hotel hotel;

	@ManyToOne
	@JoinColumn(name="senderId")
	private Customer sender;
	
	@Column
	private Timestamp timestamp = new Timestamp(new Date().getTime());	
	
	@Column
	private String customerComment;

	@Enumerated(EnumType.STRING)
	private DealStatus status;		
	
	@Column
	private String orderCode;		
	
	@Column
	private String firstName;		
	
	@Column
	private String lastName;
	
	@Column
	private String orderLocation;
	
	@Column(name = "totalPrice", columnDefinition = "Decimal(6,2) default '0.00'")
	private Double totalPrice;	
	
	@Column(name = "totalMoney", columnDefinition = "Decimal(6,2) default '0.00'")
	private Double totalMoney;

	@Column(name = "guestCustomerId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long guestCustomerId;
	
	//eugen: mappedBy entity!
	@OneToMany(mappedBy="menuOrder")
	private Set<MenuItem> menuItems = new HashSet<>();

	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", nullable = true, length = 10)
	private Date validFrom = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", nullable = true, length = 10)
	private Date validTo = new Date();
	
//	MenuOrder(Customer sender)
//	{
//		setSender(sender);
//
//		generateCode();
//
//		setInitId(ThreadLocalRandom.current().nextInt(1, 999999));
//		setConsistencyId(new Date().getTime());
//
//	}
	
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}
	
	public long getId()
	{
		return id;
	}
}
