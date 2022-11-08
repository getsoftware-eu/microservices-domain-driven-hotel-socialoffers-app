package eu.getsoftware.hotelico.customer.domain;

import java.util.Date;

/**
 * Aggregate for sub Entities
 */
public class CustomerAggregate
{
	CustomerRootEntity customerRootEntity;
	CustomerDetails customerDetails;
	CustomerPreferences customerPreferences;
	
	public CustomerAggregate(CustomerRootEntity customerRootEntity){
		this.customerRootEntity = customerRootEntity;
		this.customerDetails = customerRootEntity.getCustomerDetails();
		this.customerPreferences = customerRootEntity.getCustomerPreferences();
	}
	
	public void setHideChromePushPopup(boolean hideChromePushPopup)
	{
		customerPreferences.setHideChromePushPopup(hideChromePushPopup);
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
	
	public void setPrefferedLanguage(String prefferedLanguage)
	{
		customerPreferences.setPrefferedLanguage(prefferedLanguage);
	}
	
	public void setHideWallPopup(boolean hideWallPopup)
	{
		customerPreferences.setHideWallPopup(hideWallPopup);
		
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
	
	public void setAllowHotelNotification(boolean allowHotelNotification)
	{
		customerPreferences.setAllowHotelNotification(allowHotelNotification);
	}
	
	public void setHideHotelListPopup(boolean hideHotelListPopup)
	{
		customerPreferences.setHideHotelListPopup(hideHotelListPopup);
	}
	
	public String getProfileImageUrl()
	{
		return customerDetails.getProfileImageUrl();
	}
	
	public void setLatitude(double latitude)
	{
		customerRootEntity.getCustomerGPSPosition().setLatitude(latitude);
	}
	
	public void setLongitude(double longitude)
	{		
		customerRootEntity.getCustomerGPSPosition().setLongitude(longitude);
	}
	
	public void setHideCheckinPopup(boolean hideCheckinPopup)
	{
		customerPreferences.setHideCheckinPopup(hideCheckinPopup);
	}
	
	public Object getCity()
	{
		return customerDetails.getCity();
	}
}
