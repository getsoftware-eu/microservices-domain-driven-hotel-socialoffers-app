package eu.getsoftware.hotelico.hotel.application.port.in.iService;

import eu.getsoftware.hotelico.customer.adapter.out.persistence.model.CustomerRootEntity;

/**
 * Created by Eugen on 31.07.2015.
 */
public interface MailService
{
	
	 void sendMailToReceivers(String senderEmail, String subject, String body, String receivers, boolean bccToCurrentUser);
	
	/**
	 * This method will send compose and send the message
	 * */
	 void sendMail(String to, String subject, String body, String from);

	/**
	 * This method will send a pre-configured message
	 * */
	 void sendPreConfiguredMail(String message);
	
	 String getWellcomeMailBody(CustomerRootEntity customerEntity);
	
}