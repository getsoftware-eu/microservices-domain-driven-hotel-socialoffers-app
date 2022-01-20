package de.hotelico.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import de.hotelico.utils.HibernateUtils;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Table(name = "menu_item")
public class MenuItem implements Serializable
{

	private static final long serialVersionUID = -3552760230944489778L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column
	private int cafeId;	
	
	@Column
	private int orderIndex;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	private MenuOrder menuOrder;
	
	/**
	 * messageId -> creationTime
	 * consistencyId -> last update time
	 */
	@Column(name = "consistencyId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long consistencyId;		
	
	@Column(name = "initId", columnDefinition = "BIGINT(20) DEFAULT 0")
	private long initId;	
	
	@Column(name = "amount", columnDefinition = "int(11) DEFAULT 0")
	private int amount;

	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;
	
	@Column(name = "delimiter", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean delimiter = false;
	
	@ManyToOne
	@JoinColumn(name="hotelId")
	private Hotel hotel;

	@ManyToOne
	@JoinColumn(name="creator")
	private Customer creator;
	
	@Column
	private Timestamp timestamp;
	
//	@Temporal(TemporalType.DATE)
//	@Column(name = "validFrom", nullable = true, length = 10)
//	private Date validFrom;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name = "validTo", nullable = true, length = 10)
//	private Date validTo;
	
	@Column
	private String title;
	
	@Column
	private String shortDescription;
	
//	@Column(name = "activityArea", nullable = false)
//	private String activityArea;
	
	@Column( columnDefinition = "LONGTEXT")
	@Type(type = "text")
	private String description;	
	
	@Column(name = "pictureUrl", nullable = true, length = 250)
	private String pictureUrl;	
	
	@Column(name = "price", columnDefinition="Decimal(10,2) default '0.00'")
	private double price = 0.0;
	
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
	
	 
	
	public Timestamp getTimestamp()
	{
		return timestamp;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getShortDescription()
	{
		return shortDescription;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getPictureUrl()
	{
		return pictureUrl;
	}
	
	public double getPrice()
	{
		return price;
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
	
	public void setTimestamp(Timestamp timestamp)
	{
		this.timestamp = timestamp;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setPictureUrl(String pictureUrl)
	{
		this.pictureUrl = pictureUrl;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public int getAmount()
	{
		return amount;
	}
	
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	public void setId(int id)
	{
      //		this.id = id;
	}
	
	public MenuOrder getMenuOrder()
	{
		return menuOrder;
	}
	
	public void setMenuOrder(MenuOrder menuOrder)
	{
		this.menuOrder = menuOrder;
	}
	
	public Customer getCreator()
	{
		return creator;
	}
	
	public void setCreator(Customer creator)
	{
		this.creator = creator;
	}
	
	public int getCafeId()
	{
		return cafeId;
	}
	
	public void setCafeId(int cafeId)
	{
		this.cafeId = cafeId;
	}

	public long getInitId()
	{
		return initId;
	}

	public void setInitId(long initId)
	{
		this.initId = initId;
	}

	public int getOrderIndex()
	{
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex)
	{
		this.orderIndex = orderIndex;
	}
	
	public boolean isDelimiter()
	{
		return delimiter;
	}
	
	public void setDelimiter(boolean delimiter)
	{
		this.delimiter = delimiter;
	}
}
