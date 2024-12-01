package eu.getsoftware.hotelico.hotelapp.config.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

////	@Value("${email.host}")
////	private String host = "127.0.0.1";
//	private String host = "smtp.strato.de";
//
////	@Value("${email.port}")
////	private Integer port = 37;
//	private Integer port = 465;
//
////	@Value("${email.from}")
//	private String from = "no-reply@hotelico.de";
//
//	@Value("${email.subject}")
	private String subject = "HoteliCo";


//	@Value("${mail.protocol}")
	private String protocol = "smtp";
//	@Value("${mail.host}")
//	private String host = "smtp.strato.de";
	private String host = "smtp.gmail.com";
//	@Value("${mail.port}")
	private int port = 587;
//	@Value("${mail.smtp.auth}")
	private boolean auth = true;
//	@Value("${mail.smtp.starttls.enable}")
	private boolean starttls = true;
//	@Value("${mail.from}")
	private String from = "noreply@hotelico.de";
//	@Value("${mail.username}")
//	private String username = "sergej.neustadt@hotelico.de";
	private String username = "eugen.wi83@gmail.com";
//	@Value("${mail.password}")
//	private String password = "frankfurt15";
	private String password = "FaRsHuLeGe";
	
	private boolean checkserveridentity = true;
	
	@Bean
	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		
		javaMailSender.setJavaMailProperties(getMailProperties());

		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setProtocol(protocol);
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		

		return javaMailSender;
	}

	@Bean
	public SimpleMailMessage simpleMailMessage() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(subject);
		return simpleMailMessage;
	}
	
	private Properties getMailProperties()
	{
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", protocol);
		properties.put("mail.smtp.auth", auth);
		properties.put("mail.smtp.starttls.enable", starttls);
		properties.put("mail.smtps.ssl.checkserveridentity", checkserveridentity);
		properties.put("mail.smtps.ssl.trust", "*");
		properties.put("mail.smtp.quitwait", false);
		properties.put("mail.mime.charset", "utf8");
		properties.put("mail.debug", true);
		return properties;
	}
}