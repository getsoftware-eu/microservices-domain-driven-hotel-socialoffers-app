package de.hotelico.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hotelico.dto.CustomerDTO;
import de.hotelico.dto.ResponseDTO;
import de.hotelico.model.Customer;
import de.hotelico.repository.CustomerRepository;
import de.hotelico.repository.DealRepository;
import de.hotelico.service.CacheService;
import de.hotelico.service.CheckinService;
import de.hotelico.service.CustomerService;
import de.hotelico.service.LoginHotelicoService;
import de.hotelico.service.MailService;
import de.hotelico.service.NotificationService;
import de.hotelico.utils.ControllerUtils;
import de.hotelico.utils.HotelEvent;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 13:59
 */
@Service
public class LoginHotelicoServiceImpl implements LoginHotelicoService
{
	@Autowired
	private CustomerService customerService;	
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private MailService mailService;
		
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private CheckinService checkinService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DealRepository dealRepository;
	
	@Override
	public CustomerDTO checkLogin(String email, String password){
		
		List<Customer> customers = customerRepository.findByEmailAndActive(email, true);
		
		if(!customers.isEmpty() && password!=null)
		{
			Customer customer = customers.get(0);
			
			Long generatedPasswordHash = getCryptoHash(customer, password);
			
			if(!generatedPasswordHash.equals(customer.getPasswordHash()) && !ControllerUtils.USE_ADMIN_MODE && !"ROOTPASSWORD".equalsIgnoreCase(password))
			{
				throw new RuntimeException("Password is not correct.");
			}
			
			customer.setLogged(true);
			customer.setActive(true);
			
			customerRepository.saveAndFlush(customer);
			
			notificationService.createAndSendNotification(customer.getId(), HotelEvent.EVENT_LOGIN);
			
			cacheService.checkCustomerOnline(customer.getId());
			
			//            int hotelId = getCustomerHotelId(customer.getId());
			
			CustomerDTO out = customerService.convertMyCustomerToFullDto(customer);
			
			//            return updateOwnDtoCheckinInfo(out);
			return out;
		}
		else
			return null;
	}
	
	@Override
	public void logoutCustomer(CustomerDTO customerDto)
	{
		if(customerDto!=null && customerDto.getId()>0)
		{
			Customer logoutCustomer =  customerRepository.getOne(customerDto.getId());
			logoutCustomer.setLogged(false);
			
			//eugen: clear seen hotel activities
			//            logoutCustomer.getSeenActivities().clear();
			//logoutCustomer.updateLastSeenOnline();
			
			//TODO EUGEN: notificate logout
			//            notificateAboutLogout(customerDto.getId(), HotelEvent.EVENT_LOGOUT);
			
			customerRepository.saveAndFlush(logoutCustomer);
			
			cacheService.checkCustomerOffline(customerDto.getId());
			
		}
		customerDto.setLogged(false);
	}
	
	@Override
	public ResponseDTO requestPasswordReset(String email)
	{
		//        ResponseDto response =  new ResponseDto();
		
		List<Customer> customers = customerRepository.findByEmailAndActive(email, true);
		
		if(!customers.isEmpty())
		{
			Customer customer = customers.get(0);
			
			Date requestTime = new Date();
			customer.setLastResetPasswordRequestTime(requestTime.getTime());
			
			String saltedPasswordQuery = createPasswordQuery(customer.getLastResetPasswordRequestTime(), customer.getId(), customer.getPasswordHash());
			//            String newContext = passwordEncoder.encodePassword(saltedPasswordQuery);
			
			//            String link = pageRenderLinkSource.createPageRenderLinkWithContext(Pwreset.class, username, newContext).toAbsoluteURI();
			String link = ControllerUtils.HOST + ControllerUtils.HOST_SUFFIX + "#/app/forgotpwd/email/" + email + "/resetcode/" + saltedPasswordQuery;
			
			String body = getEncodedMailWithPasswordCode(customer, link);
			
			String subject = "Passwort zurücksetzen";
			
			String[] receiverArr = { email };
			
			try
			{
				mailService.sendMail(email, subject, body, null);
				
				customerRepository.save(customer);
				return new ResponseDTO("Ein Link für Passwort-Rücksetzung wurde per Email geschickt", false);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return new ResponseDTO("Ein Fehler ist aufgetreten", true);
			}
		}
		
		return new ResponseDTO("E-Mail " + email + " ist leider nicht gefunden", true);
	}
	
	@Override
	public CustomerDTO resetPassword(String email, String resetCode)
	{
		Customer customer = null;
		
		if (resetCode != null)
		{
			List<Customer> customers = customerRepository.findByEmailAndActive(email, true);
			
			customer = customers.get(0);
			
			long lastRequestTime = customer != null ? customer.getLastResetPasswordRequestTime() : null;
			
			String lastPasswordQuery = createPasswordQuery(lastRequestTime, customer.getId(), customer.getPasswordHash());
			//            String checkContext = passwordEncoder.encodePassword(lastPasswordQuery);
			
			if (!resetCode.equals(lastPasswordQuery))
			{
				resetCode = null;
				return null;//"Der Link ist nicht aktiv. Nehmen Sie bitte den neuesten Link oder versuchen Sie noch Mal einen Link für die Rücksetzung des Passwortes zu beantragen.";
			}
			
			Period p = new Period(new DateTime(lastRequestTime), DateTime.now());
			if (p.getHours() > 24)
			{
				resetCode = null;
				return null;//"Der Link ist veraltet. Bitte versuchen Sie noch Mal einen Link für die Rücksetzung des Passwortes zu beantragen.";
			}
		}
		
		long customerHotelId = 0L;
		
		if(customer!=null)
		{
			Long hotelIdObj = customerService.getCustomerHotelId(customer.getId()); // checkinRepository.getCustomerHotelId(customer.getId());
			
			if(hotelIdObj!=null && hotelIdObj>0)
			{
				customerHotelId = hotelIdObj;
			}
		}
		
		CustomerDTO dto = customerService.convertCustomerToDto(customer, customerHotelId);
		
		return dto;
	}
	
	/**
	 * it creates a String query for password resetting
	 *
	 */
	private String createPasswordQuery(long lastRequestTime, long customerId, long userPasswordHash)
	{
		return customerId + userPasswordHash + lastRequestTime + "";
	}
	
	/**
	 * Convert some customer information in an HTML-complain output string Especially converts some chars into HTML-ASCI codes
	 *
	 * @return HTML-complain output string
	 */
	private String getEncodedMailWithPasswordCode(Customer customer, String resetLink)
	{
		// Create String
		final StringBuffer sb = new StringBuffer();
		
		sb.append("Benutzer: ").append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n"); // Kunden Name: Max Mustermann
		sb.append("Um das Passwort zurückzusetzen, bitte bestätigen Sie folgendes Link: ").append(resetLink).append("\n");
		
		return sb.toString();
	}
	
	/**
	 * 	calculates an unique 'check' hash value for password
	 *
	 * @param customer
	 * @param initialPassword -  
	 * @return
	 */
	@Override
	public long getCryptoHash(Customer customer, String initialPassword)
	{
		if(initialPassword==null)
			return -1;
		
		long ah = Objects.hashCode(initialPassword) ^ 0xAE8F237BL;
		long bh = customer.getId() ^ 0x893AC243L;
		long ch = Objects.hashCode(customer.getClass().getSimpleName()) ^ 0x84325087L;
		
		return ah ^ (bh << 11) ^ (ch << 8) /* ^ (eh << 8)*/;
	}
	
	@Override
	public void setLogged(long customerId, boolean logged)
	{
		Customer customer = customerRepository.getOne(customerId);
		
		if(customer!=null)
		{
			customer.setLogged(logged);
			
			customerRepository.saveAndFlush(customer);
		}
	}
	
	@Override
	public CustomerDTO checkBeforeLoginProperties(CustomerDTO loggingCustomer, CustomerDTO dbCustomer)
	{
		if(loggingCustomer == null)
		{
			return dbCustomer;
		}
		
		if(dbCustomer == null)
		{
			return null;
		}
		//UPDATE ONLY FIELDS, THAT COULD BE SET WITHOUT LOGIN
		
		if(loggingCustomer.getSystemMessages()!=null && !loggingCustomer.getSystemMessages().isEmpty())
		{
			//TODO Eugen: set or just add?
			dbCustomer.setSystemMessages(loggingCustomer.getSystemMessages());
			
			if(dbCustomer.getId()>0 && dbCustomer.getSystemMessages().containsKey("guestCustomerId"))
			{
				Long guestId = Long.parseLong(dbCustomer.getSystemMessages().get("guestCustomerId"));
				
				boolean anonymGuestDealsExists = dealRepository.existAnonymDelasByGuestId(guestId);
				
				if(anonymGuestDealsExists)
				{
					Customer customer = customerRepository.getOne(dbCustomer.getId());
					
					customerService.relocateGuestDealsToLoggedCustomer(customer, guestId);
				}
			}
		}
		
		if(loggingCustomer.getHideCheckinPopup())
		{
			dbCustomer.setHideCheckinPopup(true);
		}
		
		if(loggingCustomer.getHideHotelListPopup())
		{
			dbCustomer.setHideHotelListPopup(true);
		}
		
		if(loggingCustomer.getHideCheckinPopup())
		{
			dbCustomer.setHideCheckinPopup(true);
		}
		
		
		//CHECKIN, IF NOT LOGGED WAS CHECKINED!!!!!
		if(!dbCustomer.getCheckedIn() && ( loggingCustomer.getHotelId()!=null && loggingCustomer.getHotelId()>0 || !ControllerUtils.isEmptyString(loggingCustomer.getHotelCode())))
		{
			dbCustomer.setHotelId(loggingCustomer.getHotelId());
			dbCustomer.setHotelCode(loggingCustomer.getHotelCode());
			dbCustomer.setCheckinFrom(loggingCustomer.getCheckinFrom());
			dbCustomer.setCheckinTo(loggingCustomer.getCheckinTo());
			dbCustomer = checkinService.updateCheckin(dbCustomer);
		}
		
		return dbCustomer;
	}

	
}
