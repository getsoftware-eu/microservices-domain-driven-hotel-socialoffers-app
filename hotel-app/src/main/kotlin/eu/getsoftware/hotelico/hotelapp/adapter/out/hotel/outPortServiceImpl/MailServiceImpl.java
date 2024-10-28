package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.clients.common.utils.MailValidator;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.hotel.common.dto.HotelResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.HotelActivityDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.IHotelService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.MailService;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Eugen on 31.07.2015.
 */
@Service("mailService")
//@Service
public class MailServiceImpl implements MailService
{
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private SimpleMailMessage preConfiguredMessage;
	
	@Autowired
	private ICustomerPortService customerService;	
	
	@Autowired
	private IHotelService hotelService;
	/**
	 * This method will send compose and send the message
	 * */

	public void sendMailToReceivers(String senderEmail, String subject, String body, String receivers, boolean bccToCurrentUser)
	{
		try
		{
			if (AppConfigProperties.isEmptyString(senderEmail))
			{
				throw new ValidationException("Kein E-Mail-Sender wurde eingetragen");
			}
			
			String clearSenderMail = senderEmail.contains("<") && senderEmail.contains(">") ? senderEmail.replace(">", "").split("<")[1] : senderEmail;
			
			if (!new MailValidator().validate(clearSenderMail))
			{
				throw new ValidationException("Ungültige E-Mail-Adresse: " + clearSenderMail);
			}
			
			if (AppConfigProperties.isEmptyString(receivers))
			{
				throw new ValidationException("Kein E-Mail-Empfänger wurde eingetragen");
				
			}
			String[] receiverArr = receivers.split(";|,|\\|| ");
			for (String nextRecieverMail : receiverArr)
			{
				String clearRecieverMail = nextRecieverMail.contains("<") && nextRecieverMail.contains(">") ? nextRecieverMail.replace(">", "").split("<")[1] : nextRecieverMail;
				
				if (!new MailValidator().validate(clearRecieverMail))
				{
//					alertManager.error("Ungültige E-Mail-Adresse: " + clearRecieverMail);
					throw new ValidationException("Ungültige E-Mail-Adresse: " + clearRecieverMail);
				}
			}
			
			if (AppConfigProperties.isEmptyString(subject))
			{
				throw new ValidationException("E-Mail Betreff ist leer");
			}
			if (AppConfigProperties.isEmptyString(body))
			{
				throw new ValidationException("E-Mail Text ist leer");
			}
			
//			List<String> bccList = null;
//			if (bccToCurrentUser)
//			{
////				String bccEmail = taskExecutorService.getTaskExecutor().getContact().getEmail();
////				if (!ControllerUtils.isEmptyString(bccEmail))
//				{
//					bccList = Arrays.asList(senderEmail);
//				}
//			}
			
//			mailService.send(new Mail(subject, senderEmail, new LinkedHashSet<>(Arrays.asList(receiverArr)),
//					(Collection<String>) null, bccList, (List<MailAttachment>) null)
//					.applyBody(body));
			
			String[] receiverArray = receivers.split(",");
			
			for (int i = 0; i < receiverArray.length; i++)
			{
				String nextReceiver = receiverArray[i];
				
				sendMail(nextReceiver, subject, body, senderEmail);
			}
			
			if (bccToCurrentUser)
			{
				sendMail(senderEmail, subject, body, senderEmail);
			}
			
//			alertManager.success("E-Mail wurde erfolgreich verschickt");
			
		}
		catch (Exception e)
		{
//			alertManager.error("E-Mail konnte nicht versendet werden, da ein Fehler aufgetreten ist: \"" + e.getMessage() + "\"");
			
//			return new JSONObject("success", "false");
		}
	}
	
	@Transactional
	public void sendMail(String to, String subject, String body, String from)
	{
		
		MimeMessage message = javaMailSender.createMimeMessage();

		try
		{
			message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
					
			//		message.setTo(to);
			message.setSubject(subject, "UTF-8");
			message.setHeader("Content-Type", "text/html");
			message.setHeader("charset", "UTF-8");

			//			message.setText(body, "UTF-8");
			message.setContent(body, "text/html; charset=utf-8");
			//		message.setReplyTo("noreply@hotelico.de");
			
			if(AppConfigProperties.isEmptyString(from))
			{
				message.addFrom(new InternetAddress[] { new InternetAddress("noreply@hotelico.de") });
			}
			else{	
				message.addFrom(new InternetAddress[] { new InternetAddress(from) });
			}
		}
		catch (Exception e)
		{
			
		}
		javaMailSender.send(message);
	}

	/**
	 * This method will send a pre-configured message
	 * */

	@Transactional
	public void sendPreConfiguredMail(String message)
	{
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}
	
	@Override
	public String getWellcomeMailBody(ICustomerRootEntity customerEntity)
	{
		String mailContent = "";
		String mailHotelicoContent = "";
		String mailActivityContent = "";
		
		ClassLoader classLoader = getClass().getClassLoader();
		File mailFile = new File(classLoader.getResource("wellcomeMail.htm").getFile());
		File hotelicoMailFile = new File(classLoader.getResource("hotelicoBlockInMail.htm").getFile());
		File activityMailFile = new File(classLoader.getResource("activityInMail.htm").getFile());
		
		try
		{
			BufferedReader mailReader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(mailFile), "UTF8"));	
			
			BufferedReader hotelicoReader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(hotelicoMailFile), "UTF8"));			
			
			BufferedReader activityReader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(activityMailFile), "UTF8"));
			
			String temp_content;
			
			while ((temp_content = mailReader.readLine()) != null) {
				mailContent += temp_content;
			}
			
			while ((temp_content = hotelicoReader.readLine()) != null) {
				mailHotelicoContent += temp_content;
			}
			
			while ((temp_content = activityReader.readLine()) != null) {
				mailActivityContent += temp_content;
			}
			
			mailReader.close();
			hotelicoReader.close();
			activityReader.close();
		}
		catch (UnsupportedEncodingException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		// ##########################################
		
		String hotelicoBlock = "";
		
//		for (HotelActivityDto nextActivity : hotelActivities)
		{
			String temp_activity = mailActivityContent + "";
			
//			try
			{
				temp_activity = temp_activity.replace("#[hotelico.name]",  "HoteliCo");
				temp_activity = temp_activity.replace("#[hotelico.pictureUrl]", "http://hotelico.de/"  + AppConfigProperties.HOST_SUFFIX + "/angulr/img/build/logo/logoFull.png" );
				temp_activity = temp_activity.replace("#[hotelico.shortDescription]", "shortDescription");
				temp_activity = temp_activity.replace("#[hotelico.description]", "description");
				temp_activity = temp_activity.replace("#[hotelico.url]", "https://hotelico.de/" + AppConfigProperties.HOST_SUFFIX);
			}
//			catch (UnsupportedEncodingException e)
//			{
//				e.printStackTrace();
//			}
			
			hotelicoBlock += temp_activity;
			
			mailContent = mailContent.replace("#[hotelicoBlock]", hotelicoBlock);
			
		}
		
		// ##########################################
		
		
		long hotelId = customerService.getCustomerHotelId(customerEntity.getId());
		
		HotelResponseDTO hotel = hotelService.getHotelById(hotelId);
		
		if(hotel!=null)
		{
			try
			{
				mailContent = mailContent.replace("#[hotel.name]", URLDecoder.decode(hotel.getName(), "UTF-8"));
				mailContent = mailContent.replace("#[hotel.url]", "http://hotelico.de/" + AppConfigProperties.HOST_SUFFIX + "#/app/hotelPreview/" + hotel.getId());
				mailContent = mailContent.replace("#[hotel.pictureUrl]", "http://hotelico.de/" + hotel.getPictureUrl());
				mailContent = mailContent.replace("#[hotel.wellcomeMsg]", URLDecoder.decode(hotel.getWellcomeMessage(), "UTF-8"));
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			mailContent = mailContent.replace("#[customer.mail]", customerEntity.getEmail());
			
			List<HotelActivityDTO> hotelActivities = hotelService.getHotelActivitiesByHotelId(-1, hotel.getId());
			
			//sort by orderIndex!!!
			if (hotelActivities.size() > 0) {
				Collections.sort(hotelActivities, new Comparator<HotelActivityDTO>() {
					@Override
					public int compare(final HotelActivityDTO object1, final HotelActivityDTO object2) {
						return Integer.compare(object1.getOrderIndex(), object2.getOrderIndex());
					}
				} );
			}
			
//			Stream<HotelActivityDto> sorted = hotelActivities
//					.stream()
//					.sorted((object1, object2) -> Integer.compare(object1.getOrderIndex(), object2.getOrderIndex()));
			
			String activitiesBlock = "";
			
			for (HotelActivityDTO nextActivity : hotelActivities)
			{
				String temp_activity = mailActivityContent + "";
				
				try
				{
					temp_activity = temp_activity.replace("#[activity.name]",  URLDecoder.decode(nextActivity.getTitle(), "UTF-8"));
					temp_activity = temp_activity.replace("#[activity.pictureUrl]", "http://hotelico.de/" + nextActivity.getPictureUrl());
					temp_activity = temp_activity.replace("#[activity.shortDescription]", URLDecoder.decode(nextActivity.getShortDescription(), "UTF-8"));
					temp_activity = temp_activity.replace("#[activity.description]", URLDecoder.decode(nextActivity.getDescription(), "UTF-8"));
					temp_activity = temp_activity.replace("#[activity.url]", "https://hotelico.de/" + AppConfigProperties.HOST_SUFFIX + "#/app/activityList//" + hotelId + "/" + nextActivity.getId());
				}
				catch (UnsupportedEncodingException e)
				{
					e.printStackTrace();
				}
				
				activitiesBlock += temp_activity;
			}
			
			mailContent = mailContent.replace("#[activityBlock]", activitiesBlock);
			
			
		}
		return mailContent;
	}
}
