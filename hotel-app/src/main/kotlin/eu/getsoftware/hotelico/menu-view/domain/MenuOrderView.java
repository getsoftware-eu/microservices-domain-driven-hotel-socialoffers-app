package eu.getsoftware.hotelico.menu.domain;

import eu.getsoftware.hotelico.clients.infrastructure.utils.HibernateUtils;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;
import eu.getsoftware.hotelico.deal.infrastructure.utils.DealStatus;
import eu.getsoftware.hotelico.hotel.infrastructure.entities.HotelRootEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
@Table(name = "menu_order")
public class MenuOrderView implements Serializable
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
	private HotelRootEntity hotelRootEntity;

	@ManyToOne
	@JoinColumn(name="senderId")
	private CustomerRootEntity sender;
	
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
	private Set<MenuItemView> menuItems = new HashSet<>();

	@Temporal(TemporalType.DATE)
	@Column(name = "validFrom", nullable = true, length = 10)
	private Date validFrom = new Date();

	@Temporal(TemporalType.DATE)
	@Column(name = "validTo", nullable = true, length = 10)
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
