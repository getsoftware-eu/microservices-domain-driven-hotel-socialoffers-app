package eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model;

import eu.getsoftware.hotelico.clients.common.utils.HibernateUtils;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerPreferences;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "customer_preferences", schema = "customer")
class CustomerPreferences implements ICustomerPreferences
{
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "hideCheckinPopup", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean hideCheckinPopup = false;
	
	@Column(name = "hideHotelListPopup", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean hideHotelListPopup = false;
	
	@Column(name = "hideWallPopup", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean hideWallPopup = false;
	
	@Column(name = "hideChromePushPopup", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean hideChromePushPopup = false;
	
	@Column(name = "allowHotelNotification", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean allowHotelNotification = false;
	
	@Column
	private String prefferedLanguage;
	
	public CustomerPreferences()
	{
		super();
	}
}
