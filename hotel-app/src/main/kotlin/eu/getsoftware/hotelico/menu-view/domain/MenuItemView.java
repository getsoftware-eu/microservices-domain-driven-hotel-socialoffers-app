package eu.getsoftware.hotelico.menu.domain;

import eu.getsoftware.hotelico.clients.infrastructure.utils.HibernateUtils;
import eu.getsoftware.hotelico.customer.domain.CustomerRootEntity;
import eu.getsoftware.hotelico.hotel.infrastructure.entities.HotelRootEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Eugen on 16.07.2015.
 */
@Entity
@Getter @Setter
@Table(name = "menu_item")
public class MenuItemView implements Serializable
{

	private static final long serialVersionUID = -3552760230944489778L;
	
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column
	private int cafeId;	
	
	@Column
	private int orderIndex;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	private MenuOrderView menuOrder;
	
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
	private HotelRootEntity hotelRootEntity;

	@ManyToOne
	@JoinColumn(name="creator")
	private CustomerRootEntity creator;
	
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
}
