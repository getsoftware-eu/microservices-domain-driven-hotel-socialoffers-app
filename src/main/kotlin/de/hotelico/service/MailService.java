package de.hotelico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import de.hotelico.model.Customer;

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
	
	 String getWellcomeMailBody(Customer customer);
	
}