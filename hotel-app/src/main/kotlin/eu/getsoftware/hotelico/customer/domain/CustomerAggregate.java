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
}
