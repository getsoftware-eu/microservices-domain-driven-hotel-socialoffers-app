package eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.outPortServiceImpl;

import eu.getsoftware.hotelico.clients.api.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties;
import eu.getsoftware.hotelico.hotel.application.iService.*;
import eu.getsoftware.hotelico.hotel.usecase.checkin.app.usecases.impl.CheckinService;
import eu.getsoftware.hotelico.hotel.usecase.notification.app.usecases.impl.NotificationService;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.model.CustomerDBEntity;
import eu.getsoftware.hotelico.hotelapp.adapter.out.customer.repository.CustomerRepository;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.model.HotelEvent;
import eu.getsoftware.hotelico.hotelapp.adapter.out.hotel.repository.DealRepository;
import eu.getsoftware.hotelico.hotelapp.application.customer.port.in.CustomerPortService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.domain.infrastructure.dto.ResponseDTO;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LastMessagesService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.LoginHotelicoService;
import eu.getsoftware.hotelico.hotelapp.application.hotel.port.out.iPortService.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.02.2016 13:59
 */
@Service
public class LoginHotelicoServiceImpl implements LoginHotelicoService
{
	@Autowired
	private CustomerPortService customerService;	
	
	@Autowired
	private LastMessagesService lastMessagesService;
	
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
		
		List<CustomerDBEntity> customerEntities = customerRepository.findByEmailAndActive(email, true);
		
		if(!customerEntities.isEmpty() && password!=null)
		{
			CustomerDBEntity customerEntity = customerEntities.get(0);
			
			Long generatedPasswordHash = getCryptoHash(customerEntity, password);
			
			if(!generatedPasswordHash.equals(customerEntity.getPasswordHash()) && !AppConfigProperties.USE_ADMIN_MODE && !"ROOTPASSWORD".equalsIgnoreCase(password))
			{
				throw new RuntimeException("Password is not correct.");
			}
			
			customerEntity.setLogged(true);
			customerEntity.setActive(true);
			
			customerRepository.saveAndFlush(customerEntity);
			
			notificationService.createAndSendNotification(customerEntity.getId(), HotelEvent.EVENT_LOGIN);
			
			lastMessagesService.checkCustomerOnline(customerEntity.getId());
			
			//            int hotelId = getCustomerHotelId(customer.getId());
			
			CustomerDTO out = customerService.convertMyCustomerToFullDto(customerEntity);
			
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
			CustomerDBEntity logoutCustomerRootEntity =  customerRepository.getOne(customerDto.getId());
			logoutCustomerRootEntity.setLogged(false);
			
			//eugen: clear seen hotel activities
			//            logoutCustomer.getSeenActivities().clear();
			//logoutCustomer.updateLastSeenOnline();
			
			//TODO EUGEN: notificate logout
			//            notificateAboutLogout(customerDto.getId(), HotelEvent.EVENT_LOGOUT);
			
			customerRepository.saveAndFlush(logoutCustomerRootEntity);
			
			lastMessagesService.checkCustomerOffline(customerDto.getId());
			
		}
		customerDto.setLogged(false);
	}
	
	@Override
	public ResponseDTO requestPasswordReset(String email)
	{
		//        ResponseDto response =  new ResponseDto();
		
		List<CustomerDBEntity> customerEntities = customerRepository.findByEmailAndActive(email, true);
		
		if(!customerEntities.isEmpty())
		{
			CustomerDBEntity customerEntity = customerEntities.get(0);
			
			Date requestTime = new Date();
			customerEntity.setLastResetPasswordRequestTime(requestTime.getTime());
			
			String saltedPasswordQuery = createPasswordQuery(customerEntity.getLastResetPasswordRequestTime(), customerEntity.getId(), customerEntity.getPasswordHash());
			//            String newContext = passwordEncoder.encodePassword(saltedPasswordQuery);
			
			//            String link = pageRenderLinkSource.createPageRenderLinkWithContext(Pwreset.class, username, newContext).toAbsoluteURI();
			String link = AppConfigProperties.HOST + AppConfigProperties.HOST_SUFFIX + "#/app/forgotpwd/email/" + email + "/resetcode/" + saltedPasswordQuery;
			
			String body = getEncodedMailWithPasswordCode(customerEntity, link);
			
			String subject = "Passwort zurücksetzen";
			
			String[] receiverArr = { email };
			
			try
			{
				mailService.sendMail(email, subject, body, null);
				
				customerRepository.save(customerEntity);
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
		CustomerDBEntity customerEntity = null;
		
		if (resetCode != null)
		{
			List<CustomerDBEntity> customerEntities = customerRepository.findByEmailAndActive(email, true);
			
			customerEntity = customerEntities.get(0);
			
			long lastRequestTime = customerEntity != null ? customerEntity.getLastResetPasswordRequestTime() : null;
			
			String lastPasswordQuery = createPasswordQuery(lastRequestTime, customerEntity.getId(), customerEntity.getPasswordHash());
			//            String checkContext = passwordEncoder.encodePassword(lastPasswordQuery);
			
			if (!resetCode.equals(lastPasswordQuery))
			{
				resetCode = null;
				return null;//"Der Link ist nicht aktiv. Nehmen Sie bitte den neuesten Link oder versuchen Sie noch Mal einen Link für die Rücksetzung des Passwortes zu beantragen.";
			}
			
			LocalDateTime lastRequest = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastRequestTime), ZoneId.systemDefault());
			
			Period p = Period.between(lastRequest.toLocalDate(), LocalDate.now());
			if (p.getDays() > 1)
			{
				resetCode = null;
				return null;//"Der Link ist veraltet. Bitte versuchen Sie noch Mal einen Link für die Rücksetzung des Passwortes zu beantragen.";
			}
		}
		
		long customerHotelId = 0L;
		
		if(customerEntity !=null)
		{
			Long hotelIdObj = customerService.getCustomerHotelId(customerEntity.getId()); // checkinRepository.getCustomerHotelId(customer.getId());
			
			if(hotelIdObj!=null && hotelIdObj>0)
			{
				customerHotelId = hotelIdObj;
			}
		}
		
		CustomerDTO dto = customerService.convertCustomerToDto(customerEntity, customerHotelId);
		
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
	private String getEncodedMailWithPasswordCode(CustomerDBEntity customerEntity, String resetLink)
	{
		// Create String
		final StringBuffer sb = new StringBuffer();
		
		sb.append("Benutzer: ").append(customerEntity.getFirstName()).append(" ").append(customerEntity.getLastName()).append("\n"); // Kunden Name: Max Mustermann
		sb.append("Um das Passwort zurückzusetzen, bitte bestätigen Sie folgendes Link: ").append(resetLink).append("\n");
		
		return sb.toString();
	}
	
	/**
	 * 	calculates an unique 'check' hash value for password
	 *
	 * @param customerEntity
	 * @param initialPassword -  
	 * @return
	 */
	@Override
	public long getCryptoHash(CustomerDBEntity customerEntity, String initialPassword)
	{
		if(initialPassword==null)
			return -1;
		
		long ah = Objects.hashCode(initialPassword) ^ 0xAE8F237BL;
		long bh = customerEntity.getId() ^ 0x893AC243L;
		long ch = Objects.hashCode(customerEntity.getClass().getSimpleName()) ^ 0x84325087L;
		
		return ah ^ (bh << 11) ^ (ch << 8) /* ^ (eh << 8)*/;
	}
	
	@Override
	public void setLogged(long customerId, boolean logged)
	{
		CustomerDBEntity customerEntity = customerRepository.getOne(customerId);
		
		if(customerEntity !=null)
		{
			customerEntity.setLogged(logged);
			
			customerRepository.saveAndFlush(customerEntity);
		}
	}
	
	@Override
	public Optional<CustomerDTO> checkBeforeLoginProperties(CustomerDTO loggingCustomer, CustomerDTO dbCustomer)
	{
		if(loggingCustomer == null)
		{
			return Optional.ofNullable(dbCustomer);
		}
		
		if(dbCustomer == null)
		{
			return Optional.empty();
		}
		//UPDATE ONLY FIELDS, THAT COULD BE SET WITHOUT LOGIN
		
		if(loggingCustomer.getSystemMessages()!=null && !loggingCustomer.getSystemMessages().isEmpty())
		{
			//TODO Eugen: set or just add?
			dbCustomer.setSystemMessages(loggingCustomer.getSystemMessages());
			
			if(dbCustomer.getId()>0 && dbCustomer.getSystemMessages().containsKey("guestCustomerId"))
			{
				Long guestId = Long.parseLong(String.valueOf(dbCustomer.getSystemMessages().get("guestCustomerId")));
				
				boolean anonymGuestDealsExists = dealRepository.existAnonymDelasByGuestId(guestId);
				
				if(anonymGuestDealsExists)
				{
					CustomerDBEntity customerEntity = customerRepository.getOne(dbCustomer.getId());
					
					customerService.relocateGuestDealsToLoggedCustomer(customerEntity, guestId);
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
		if(!dbCustomer.getCheckedIn() && ( loggingCustomer.getHotelId()>0 || !AppConfigProperties.isEmptyString(loggingCustomer.getHotelCode())))
		{
			dbCustomer.setHotelId(loggingCustomer.getHotelId());
			dbCustomer.setHotelCode(loggingCustomer.getHotelCode());
			dbCustomer.setCheckinFrom(loggingCustomer.getCheckinFrom());
			dbCustomer.setCheckinTo(loggingCustomer.getCheckinTo());
			dbCustomer = checkinService.updateCheckin(dbCustomer);
		}
		
		return Optional.of(dbCustomer);
	}

	
}
