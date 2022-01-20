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

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Table(name = "menu_order")
public class MenuOrder implements Serializable
{

	private static final long serialVersionUID = -3552760230944489778L;
	
	@Id
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
	
	
	public long getConsistencyId()
	{
		return consistencyId;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public Hotel getHotel()
	{
		return hotel;
	}
	
	public Customer getSender()
	{
		return sender;
	}
	
	public Timestamp getTimestamp()
	{
		return timestamp;
	}
	
	public void setConsistencyId(long consistencyId)
	{
		this.consistencyId = consistencyId;
	}
	
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public void setHotel(Hotel hotel)
	{
		this.hotel = hotel;
	}
	
	public void setSender(Customer sender)
	{
		this.sender = sender;
	}
	
	public void setTimestamp(Timestamp timestamp)
	{
		this.timestamp = timestamp;
	}
	
	public void setId(int id)
	{
      //		this.id = id;
	}
	
	public String getCustomerComment()
	{
		return customerComment;
	}
	
	public Double getTotalPrice()
	{
		return totalPrice;
	}
	
	public Set<MenuItem> getMenuItems()
	{
		return menuItems;
	}
	
	
	public void setCustomerComment(String customerComment)
	{
		this.customerComment = customerComment;
	}
	
	public void setTotalPrice(Double totalPrice)
	{
		this.totalPrice = totalPrice;
	}
	
	public void setMenuItems(Set<MenuItem> menuItems)
	{
		this.menuItems = menuItems;
	}
	
	public long getInitId()
	{
		return initId;
	}
	
	public void setInitId(long initId)
	{
		this.initId = initId;
	}
	
	public boolean isOrderInRoom()
	{
		return orderInRoom;
	}
	
	public String getOrderLocation()
	{
		return orderLocation;
	}
	
	public void setOrderInRoom(boolean orderInRoom)
	{
		this.orderInRoom = orderInRoom;
	}
	
	public void setOrderLocation(String orderLocation)
	{
		this.orderLocation = orderLocation;
	}

	public DealStatus getStatus()
	{
		return status;
	}

	public String getOrderCode()
	{
		return orderCode;
	}

	public void setStatus(DealStatus status)
	{
		this.status = status;
	}

	public void setOrderCode(String orderCode)
	{
		this.orderCode = orderCode;
	}

	public long getGuestCustomerId()
	{
		return guestCustomerId;
	}

	public void setGuestCustomerId(long guestCustomerId)
	{
		this.guestCustomerId = guestCustomerId;
	}

	public Date getValidFrom()
	{
		return validFrom;
	}

	public Date getValidTo()
	{
		return validTo;
	}

	public void setValidFrom(Date validFrom)
	{
		this.validFrom = validFrom;
	}

	public void setValidTo(Date validTo)
	{
		this.validTo = validTo;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Double getTotalMoney()
	{
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney)
	{
		this.totalMoney = totalMoney;
	}
}
