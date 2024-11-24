package eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService;

import eu.getsoftware.hotelico.clients.api.clients.dto.entity.CustomerDTO;

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
	
	 String getWellcomeMailBody(CustomerDTO customerEntity);
	
}