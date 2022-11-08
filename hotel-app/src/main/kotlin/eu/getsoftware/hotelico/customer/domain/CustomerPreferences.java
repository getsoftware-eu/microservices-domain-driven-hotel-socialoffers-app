package eu.getsoftware.hotelico.customer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import eu.getsoftware.hotelico.domain.utils.HibernateUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "customer_preferences")
class CustomerPreferences
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
	
	@Column(name = "setAllowHotelNotification", columnDefinition = HibernateUtils.ColumnDefinition.BOOL_DEFAULT_FALSE)
	private boolean setAllowHotelNotification = false;
	
	@Column
	private String prefferedLanguage;
	
	public CustomerPreferences()
	{
		super();
	}
}
