package eu.getsoftware.hotelico.hotelapp.application.customer.domain;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerRootEntity.CustomerRootEntityBuilder;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerDetails;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerPreferences;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;

import java.util.Date;
import java.util.Optional;

/**
 * Aggregate for sub Entities: Any internal structural change will be not visible outside!
 * 
 * all setters for customerRootEntity, outside via aggregate: not direct jpa-entity setter logik!!!
 * and all setters and getters for hidden sub-classes
 */
public class CustomerAggregate
{
	private final CustomerRootEntityBuilder customerBuilder;
	private final ICustomerRootEntity customerRootEntity;
	private final ICustomerDetails customerDetails;
	private final ICustomerPreferences customerPreferences;
	
	public CustomerAggregate(CustomerRootEntity customerRootEntity){
		this.customerRootEntity = customerRootEntity;
		this.customerBuilder = customerRootEntity.toBuilder();
		this.customerDetails = customerRootEntity.getCustomerDetails();
		this.customerPreferences = customerRootEntity.getCustomerPreferences();
	}
	
	// SETTERS for customerPreferences
	
	public void setHideChromePushPopup(boolean hideChromePushPopup)
	{
		customerPreferences.setHideChromePushPopup(hideChromePushPopup);
	}
	
	public void setPrefferedLanguage(String prefferedLanguage)
	{
		customerPreferences.setPrefferedLanguage(prefferedLanguage);
	}
	
	public void setHideWallPopup(boolean hideWallPopup)
	{
		customerPreferences.setHideWallPopup(hideWallPopup);
	}
	
	public void setAllowHotelNotification(boolean allowHotelNotification)
	{
		customerPreferences.setAllowHotelNotification(allowHotelNotification);
	}
	
	public void setHideHotelListPopup(boolean hideHotelListPopup)
	{
		customerPreferences.setHideHotelListPopup(hideHotelListPopup);
	}
	
	public void setHideCheckinPopup(boolean hideCheckinPopup)
	{
		customerPreferences.setHideCheckinPopup(hideCheckinPopup);
	}
	
	public String getPrefferedLanguage()
	{
		return customerPreferences.getPrefferedLanguage();
	}
	
	public boolean isAllowHotelNotification()
	{
		return customerPreferences.isAllowHotelNotification();
	}
	
	// SETTERS for customerDetails
	
	public String getCity()
	{
		return customerDetails.getCity();
	}
	
	public Optional<String> getProfileImageLink()
	{
		return customerDetails.getProfileImageLink();
	}
	
	public void setProfileImageLink(String link)
	{
		customerDetails.setProfileImageLink(link);
	}
	
	public void setBirthday(Date birthdayDate)
	{
		customerDetails.setBirthday(birthdayDate);
	}
	
	public void setProfileImageUrl(String s)
	{
		customerDetails.setProfileImageUrl(s);
	}
	
	public void setCity(String city)
	{
		customerDetails.setProfileImageUrl(city);
	}
	
	public void setCompany(String company)
	{
		customerDetails.setCompany(company);
	}
	
	public void setJobTitle(String jobTitle)
	{
		customerDetails.setJobTitle(jobTitle);
	}
	
	public void setOriginalCity(String originalCity)
	{
		customerDetails.setOriginalCity(originalCity);
	}
	
	public void setJobDescriptor(String jobDescriptor)
	{
		customerDetails.setJobDescriptor(jobDescriptor);
	}
	
	public String getProfileImageUrl()
	{
		return customerDetails.getProfileImageUrl();
	}
	
	// SETTERS for customerRootEntity
	
	public void setConsistencyId(long consistencyId)
	{
		customerRootEntity.setConsistencyId(consistencyId);
	}
	
	public long getId()
	{
		return customerRootEntity.getId();
	}
	
	public ICustomerRootEntity getCustomerEntity(){
		return customerRootEntity;
	}
	
	public void setLatitude(double latitude)
	{
		customerRootEntity.getCustomerGPSPosition().setLatitude(latitude);
	}
	
	public void setLongitude(double longitude)
	{		
		customerRootEntity.getCustomerGPSPosition().setLongitude(longitude);
	}
	
	public void setHotelStaff(boolean b)
	{
		customerRootEntity.setHotelStaff(b);
	}
	
	public void setLogged(boolean b)
	{
		customerRootEntity.setLogged(b);
	}
	
	public void setPasswordValue(String password)
	{
		customerRootEntity.setPasswordValue(password);
	}
	
	public void setPasswordHash(long passwordHash)
	{
		customerRootEntity.setPasswordHash(passwordHash);
	}
	
	public void setGuestAccount(boolean b)
	{
		customerRootEntity.setGuestAccount(b);
	}
	
	public void setActive(boolean b) 
	{
		customerRootEntity.setActive(b);
	}
	
	public void setFirstName(String firstName)
	{
		customerRootEntity.setFirstName(firstName);
	}
	
	public void setLastName(String lastName)
	{		
		customerRootEntity.setLastName(lastName);
	}
	
	public void setStatus(String status)
	{
		customerRootEntity.setStatus(status);
	}
	
	public void setSex(String sex)
	{
		customerRootEntity.setSex(sex);
	}
	
	public void setShowAvatar(boolean showAvatar)
	{
		customerRootEntity.setShowAvatar(showAvatar);
	}
	
	public void setShowInGuestList(boolean showInGuestList)
	{
		customerRootEntity.setShowInGuestList(showInGuestList);
	}
	
	public void setEmail(String email)
	{
		customerRootEntity.setEmail(email);
	}
	
	public void setLinkedInId(String linkedInId)
	{
		customerRootEntity.setLinkedInId(linkedInId);
	}
	
	public void setFacebookId(String facebookId)
	{
		customerRootEntity.setFacebookId(facebookId);
	}
	
	public void setPushRegistrationId(String s)
	{
		customerRootEntity.setPushRegistrationId(s);
	}
	
	public double getLatitude()
	{
		return customerRootEntity.getCustomerGPSPosition().getLatitude();
	}
	
	public double getLongitude()
	{
		return customerRootEntity.getCustomerGPSPosition().getLongitude();
	}
}
