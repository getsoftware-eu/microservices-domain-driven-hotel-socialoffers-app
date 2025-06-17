package eu.getsoftware.hotelico.client.adapter.out.persistence.model;

import eu.getsoftware.hotelico.clients.common.utils.DealStatus;
import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "menu_orders")
public class MenuOrderMappedEntity implements Serializable
{
	private static final long serialVersionUID = -3552760230944489778L;
	
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	//Eugen: not RootEntity, but its id
	//	@ManyToOne
	@JoinColumn(name="hotelId")
	private long hotelRootEntityId;
	
	//Eugen: not RootEntity, but its id
	//	@ManyToOne
	@JoinColumn(name="senderDomainId")
	private long senderId;
	
	@Column(name = "initId", columnDefinition = HibernateUtils.ColumnDefinition.LONG_20_DEFAULT_0)
	private long initId;
	
	/**
	 * messageId -> creationTime
	 * consistencyId -> last update time
	 */
	@Column(name = "consistencyId", columnDefinition = HibernateUtils.ColumnDefinition.LONG_20_DEFAULT_0)
	private long consistencyId;

	@Column(name = "active", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_TRUE)
	private boolean active = true;	
	
	@Column(name = "orderInRoom", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean orderInRoom = true;
	
	@Column
	private Timestamp timestamp = new Timestamp(System.currentTimeMillis());	
	
	@Column
	private String customerComment;

	@Enumerated(EnumType.STRING)
	@Setter
	private DealStatus status;		
	
	@Column
	private String orderCode;		
	
	@Column
	private String firstName;		
	
	@Column
	private String lastName;
	
	@Column
	private String orderLocation;
	
	@Column(name = "totalPrice", columnDefinition = HibernateUtils.ColumnDefinition.PRICE_DEFAULT_0)
	private Double totalPrice;	
	
	@Column(name = "totalMoney", columnDefinition = HibernateUtils.ColumnDefinition.PRICE_DEFAULT_0)
	private Double totalMoney;

	@Column(name = "guestCustomerId", columnDefinition = HibernateUtils.ColumnDefinition.LONG_20_DEFAULT_0)
	private long guestCustomerId;
	
	//eugen: mappedBy entity!
	@OneToMany(mappedBy="menuOrder")
	private Set<MenuItemMappedEntity> menuItems = new HashSet<>();

	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", length = 10)
	private Date validFrom = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", length = 10)
	private Date validTo = new Date();
	
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}
	
	public long getId()
	{
		return id;
	}
}
