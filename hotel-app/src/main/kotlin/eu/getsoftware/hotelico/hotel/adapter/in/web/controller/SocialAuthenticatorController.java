package eu.getsoftware.hotelico.hotel.adapter.in.web.controller;

import eu.getsoftware.hotelico.adapter.in.web.controller.BasicController;
import eu.getsoftware.hotelico.clients.common.dto.CustomerDTO;
import eu.getsoftware.hotelico.common.utils.ControllerUtils;
import eu.getsoftware.hotelico.customer.adapter.out.persistence.repository.CustomerRepository;
import eu.getsoftware.hotelico.customer.application.port.in.iservice.CustomerPortService;
import eu.getsoftware.hotelico.hotel.application.port.in.iService.LastMessagesService;
import eu.getsoftware.hotelico.hotel.application.port.in.iService.LoginHotelicoService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

	/**
	 * Created by Eugen on 08.07.2015.
	 */
@RestController
@RequestMapping("/social")
public class SocialAuthenticatorController extends BasicController
{

	private Facebook facebook;

	@Inject
	public SocialAuthenticatorController(Facebook facebook) {
		this.facebook = facebook;
	}

	@Autowired
	private CustomerPortService customerService;

	@Autowired
	private LastMessagesService lastMessagesService;
	
	@Autowired
	private LoginHotelicoService loginService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;


	public static final String STATE = "state";
	private static final String applicationHost= ControllerUtils.HOST + ControllerUtils.HOST_SUFFIX;
	private static final String linkedInCallBackPath= "social/auth/linkedIn/callback";
	private static final String facebookCallBackPath= "social/auth/facebook/callback";
	private static final String linkedInClientId = "77rxbh8n0ta2fc";
	private static final String linkedInClientSecret = "aW3L9r9racGYIV5I";
	private static final String facebookClientSecret = "f492cdd7e0d9aee36ce8446c5caa440d";

	private static final String facebookClientId = "383626641847356";

//	private LinkedInConnectionFactory linkedInConnectionFactory =
//			new LinkedInConnectionFactory("77rxbh8n0ta2fc", "aW3L9r9racGYIV5I");

	private FacebookConnectionFactory facebookConnectionFactory =
			new FacebookConnectionFactory("383626641847356", "f492cdd7e0d9aee36ce8446c5caa440d");

	@Autowired
	private ConnectionRepository connectionRepository;
	//	private LinkedInConnectionFactory linkedInConnectionFactory;

	//	@Autowired
	//	public SocialAuthenticatorController(
	////			@Value("#{linkedIn.clientId}")
	////			String linkedInClientId,
	////			@Value("#{linkedIn.clientSecret}")
	////			String linkedInClientSecret,
	////			@Value("#{application.host}")
	////			String applicationHost
	//	) {
	////		this.applicationHost = applicationHost;
	//		String linkedInClientId = "77rxbh8n0ta2fc";
	//		String linkedInClientSecret = "aW3L9r9racGYIV5I";
	//		this.applicationHost = "http://localhost:8080";
	//	
	//		linkedInConnectionFactory =
	//				new LinkedInConnectionFactory(linkedInClientId, linkedInClientSecret);
	//	}

	//
	// SocialConfigurer implementation methods
	//


	//	@Bean
	//	public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
	//		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter());
	//	}

//	@RequestMapping("/auth/linkedIn")
//	public RedirectView startAuthentication(HttpSession session)
//	//			throws OAuthSystemException
//	{
//		String state = UUID.randomUUID().toString();
//		session.setAttribute(STATE, state);
//
//		OAuth2Operations oauthOperations =
//				linkedInConnectionFactory.getOAuthOperations();
//		OAuth2Parameters params = new OAuth2Parameters();
//		params.setRedirectUri(applicationHost + linkedInCallBackPath);
//		params.setState(state);
//
//		String authorizeUrl = oauthOperations.buildAuthorizeUrl(
//				GrantType.AUTHORIZATION_CODE, params);
//		return new RedirectView(authorizeUrl);
//	}

	
	@RequestMapping("/auth/facebook")
	public RedirectView startFacebbokAuthentication(HttpSession session)
	//			throws OAuthSystemException
	{
		String state = UUID.randomUUID().toString();
		session.setAttribute(STATE, state);

		OAuth2Operations oauthOperations =
				facebookConnectionFactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri(applicationHost + facebookCallBackPath);
		params.setState(state);

		String authorizeUrl = oauthOperations.buildAuthorizeUrl(
				GrantType.AUTHORIZATION_CODE, params);
		return new RedirectView(authorizeUrl);
	}
	
	/**
	 * new generic social request for all social apps
	 * @param session
	 * @return
	 */
	@RequestMapping(value ="/login/auth/{socialType}/{loginId}", method = RequestMethod.POST)
	public @ResponseBody CustomerDTO postSocialAuthentication(@RequestBody /*@ModelAttribute(ControllerUtils.SESSION_CUSTOMER)*/ CustomerDTO sessionCustomer, @PathVariable String socialType, @PathVariable String loginId, HttpSession session)
	{
		lastMessagesService.addWaitingSocialDto(loginId, sessionCustomer);

		return sessionCustomer;
	}

	@RequestMapping(value ="/login/auth/{socialType}/{loginId}")
	public RedirectView getSocialAuthentication(@PathVariable String socialType, @PathVariable String loginId, HttpSession session)
	//			throws OAuthSystemException
	{
		session.setAttribute(STATE, loginId);

		OAuth2Operations oauthOperations = null;
		OAuth2Parameters params = new OAuth2Parameters();

		if(socialType.equalsIgnoreCase("facebook"))
		{
			oauthOperations =
					facebookConnectionFactory.getOAuthOperations();
			params.setRedirectUri(applicationHost + facebookCallBackPath);
		}
//		else if(socialType.equalsIgnoreCase("linkedIn"))
//		{
//			oauthOperations =
//					linkedInConnectionFactory.getOAuthOperations();
//			params.setRedirectUri(applicationHost + linkedInCallBackPath);
//		}

		String authorizeUrl = "";

		if(oauthOperations!=null)
		{
			params.setState(loginId);
			authorizeUrl = oauthOperations.buildAuthorizeUrl(
					GrantType.AUTHORIZATION_CODE, params);

		}

		return new RedirectView(authorizeUrl);
	}

	@RequestMapping(value = "/auth/linkedIn/callback")
	public RedirectView linkedInCallback(@RequestParam(required=false) String code, @RequestParam("state") String state, HttpSession session) {

//		//TODO eugen: we save something in session of client, than we cann proof it !!!!!!!!
//		//TODO save mor in SESSION
//		//		String stateFromSession = (String) session.getAttribute(STATE);
//		//		session.removeAttribute(STATE);
//		//		if (!state.equals(stateFromSession)) {
//		//			return new RedirectView("/access.login");
//		//		}
//
//		String redirectUrl = "#/access/login";
//
//		if(ControllerUtils.isEmptyString(code))
//		{
//			return new RedirectView(applicationHost+redirectUrl);
//		}
//
//		// upon receiving the callback from the provider:
//		AccessGrant accessGrant = getLinkedInAccessGrant(code);
//
//		Connection<LinkedIn> connection =
//				linkedInConnectionFactory.createConnection(accessGrant);
//
//		String linkedInId = getLinkedInUserId(connection);
//
//		CustomerDto foundLinkedInCustomer = customerService.getByLinkedInId(linkedInId);
//
//		UserProfile userProfile = connection.fetchUserProfile();
//
//		redirectUrl = "#/app/hotel";
//
//		//		CustomerDto resultCustomer = null;
//
//		if(foundLinkedInCustomer==null)
//		{
//			String linkedInEmail = userProfile.getEmail();
//
//			if(linkedInEmail!=null)
//			{
//				foundLinkedInCustomer = customerService.getByEmail(linkedInEmail);
//			}
//			//TODO eugen: if found by email, add linkedIn ID
//			//			foundLinkedInCustomer.setLinkedInId(linkedInId);
//		}
//		
//		//########## social cache ################
//
//		CustomerDto tempfoundCacheSocialCustomer = cacheService.getWaitingSocialCustomer(state);
//
//		if(foundLinkedInCustomer==null && tempfoundCacheSocialCustomer!=null)
//		{
//			foundLinkedInCustomer = tempfoundCacheSocialCustomer;
//		}
//		else if(foundLinkedInCustomer!=null && tempfoundCacheSocialCustomer!=null)
//		{
//			foundLinkedInCustomer = loginService.checkBeforeLoginProperties(tempfoundCacheSocialCustomer, foundLinkedInCustomer);
//		}
//		
//		// ########################################
//		
//		if(foundLinkedInCustomer==null)
//		{
//			if(foundLinkedInCustomer==null)
//			{
//				foundLinkedInCustomer = new CustomerDto();
//			}
//
//			//			foundLinkedInCustomer.setLinkedInId(linkedInId);
//			foundLinkedInCustomer.setFirstName(userProfile.getFirstName());
//			foundLinkedInCustomer.setLastName(userProfile.getLastName());
//
//			foundLinkedInCustomer.setSex("m");
//
//			if(userProfile.getEmail()!=null)
//				foundLinkedInCustomer.setEmail(userProfile.getEmail());
//
//			if(connection.getImageUrl()!=null)
//				foundLinkedInCustomer.setProfileImageUrl(connection.getImageUrl());
//
//			foundLinkedInCustomer.setLogged(true);
//
//			foundLinkedInCustomer = customerService.addLinkedInCustomer(foundLinkedInCustomer, linkedInId);
//
//			//			redirectUrl = "#/app/me/true";
//			redirectUrl = "#/app/checkin";
//		}
//
//		if(foundLinkedInCustomer!=null)
//		{
//			foundLinkedInCustomer.setLogged(true);
//
//			loginService.setLogged(foundLinkedInCustomer.getId(), true);
//
//			cacheService.checkCustomerOnline(foundLinkedInCustomer.getId());
//			//			try
//			//			{
//			//				Customer entity = customerService.getEntityById(foundLinkedInCustomer.getId());
//			//
//			//				if(entity!=null){
//			//					entity.setLogged(true);
//			//					entity.updateLastSeenOnline();
//			//					customerRepository.saveAndFlush(entity);
//			//				}
//			//			}
//			//			catch (Error e)
//			//			{
//			//				;
//			//			}
//
//			session.setAttribute(ControllerUtils.SESSION_CUSTOMER_ID, foundLinkedInCustomer.getId());
//			session.setAttribute(ControllerUtils.SESSION_CUSTOMER, foundLinkedInCustomer);
//		}
		String redirectUrl = "";
		return new RedirectView(applicationHost+redirectUrl);
	}

	@RequestMapping("/auth/facebook/callback")
	public RedirectView callBack(@RequestParam(required=false) String code, @RequestParam("state") String state, HttpSession session) {

		//		String stateFromSession = (String) session.getAttribute(STATE);
		//		session.removeAttribute(STATE);
		//		if (!state.equals(stateFromSession)) {
		//			return new RedirectView("/login");
		//		}

		String redirectUrl = "#/access/login";

		if(ControllerUtils.isEmptyString(code))
		{
			return new RedirectView(applicationHost+redirectUrl);
		}

		AccessGrant accessGrant = getFacebookAccessGrant(code);

		Facebook facebook = new FacebookTemplate(accessGrant.getAccessToken());
		
		Connection<Facebook> connection =
				facebookConnectionFactory.createConnection(accessGrant);

		User profile = connection.getApi().userOperations().getUserProfile();
		
//		Connection<Facebook> connection2 = connectionRepository.findPrimaryConnection(Facebook.class);
//		Facebook facebook2 = connection2 != null ? connection2.getApi() : null;
//		FacebookProfile profile2 = facebook2.userOperations().getUserProfile();
		
//		FacebookProfile profile2 = facebook.userOperations().getUserProfile();

		String facebookUserId = getFacebookUserId(connection);

		session.setAttribute("facebookUserId", facebookUserId);

		Optional<CustomerDTO> foundFacebookCustomer = customerService.getByFacebookId(facebookUserId);

		UserProfile userProfile = connection.fetchUserProfile();

		redirectUrl = "#/app/hotel";

		String facebookEmail = userProfile.getEmail();

		if(profile!=null && facebookEmail==null)
		{
			facebookEmail = profile.getEmail();
		}

		if(foundFacebookCustomer.isEmpty())
		{
			if(facebookEmail != null)
			{
				foundFacebookCustomer = customerService.getByEmail(facebookEmail);
			}

			//TODO eugen: if found by email, add facebookId
		}

		//########## social cache ################
		
		Optional<CustomerDTO> tempfoundCacheSocialCustomer = lastMessagesService.getWaitingSocialCustomer(state);
		
		if(foundFacebookCustomer.isEmpty() && tempfoundCacheSocialCustomer.isPresent())
		{
			foundFacebookCustomer = tempfoundCacheSocialCustomer;
		}
		else if(foundFacebookCustomer.isPresent() && tempfoundCacheSocialCustomer.isPresent())
		{
			foundFacebookCustomer = loginService.checkBeforeLoginProperties(tempfoundCacheSocialCustomer.get(), foundFacebookCustomer.get());
		}
		
		// ########################################
		CustomerDTO foundFacebookCustomerDTO = (foundFacebookCustomer.isEmpty())? new CustomerDTO(0) : foundFacebookCustomer.get();
		
		if(foundFacebookCustomerDTO.getFirstName()==null)
		{
			if (userProfile.getFirstName() != null)
			{
				foundFacebookCustomerDTO.setFirstName(userProfile.getFirstName());
				foundFacebookCustomerDTO.setLastName(userProfile.getLastName());
			}
			else if(profile!=null && profile.getFirstName()!=null)
			{
				foundFacebookCustomerDTO.setFirstName(userProfile.getFirstName());
				foundFacebookCustomerDTO.setLastName(userProfile.getLastName());
			}
			else{

				String fullName = userProfile.getName();

				if(fullName!=null)
				{
					String[] split = fullName.split(" ");

					String lastName = split[split.length - 1];
					String firstName = fullName.replace(lastName,"").trim();
					
					foundFacebookCustomerDTO.setFirstName(firstName);
					foundFacebookCustomerDTO.setLastName(lastName);
				}
			}

			if(facebookEmail!=null)
				foundFacebookCustomerDTO.setEmail(userProfile.getEmail());			
			
			if(profile!=null && Locale.GERMAN.equals(profile.getLocale()))
			{
				if(!ControllerUtils.isEmptyString(profile.getLocale().getLanguage()))
				{
					foundFacebookCustomerDTO.setPrefferedLanguage(profile.getLocale().getLanguage().toLowerCase());
				}
			}

			if(profile!=null && profile.getGender()!=null)
			{
				String gender = "female".equalsIgnoreCase(profile.getGender())? "f" : "m";
				foundFacebookCustomerDTO.setSex(gender);
			}
			else
				foundFacebookCustomerDTO.setSex("m");

			//			if(profile2!=null)
			//			{
			//				foundFacebookCustomer.setOriginalCity(profile2.getHometown().getName());
			//				foundFacebookCustomer.setWebsite(profile2.getWebsite());
			////				foundFacebookCustomer.setWebsite(profile2.getLanguages().iterator());
			//
			//				Iterator<EducationEntry> educationIterator = profile2.getEducation().iterator();
			//				if(educationIterator.hasNext())
			//				{
			//					String education = educationIterator.next().getDtoType();
			//					foundFacebookCustomer.setEducation(education);
			//
			//				}
			//
			//				Iterator<WorkEntry> workIterator = profile2.getWork().iterator();
			//					
			//				WorkEntry work = workIterator.next();
			//				foundFacebookCustomer.setJobTitle(work.toString());
			//				foundFacebookCustomer.setEmployer(work.getEmployer().getName());
			//			}

			if(connection.getImageUrl()!=null)
				foundFacebookCustomerDTO.setProfileImageUrl(connection.getImageUrl());
			
			foundFacebookCustomerDTO.setLogged(true);
			
			 customerService.addFacebookCustomer(foundFacebookCustomerDTO, facebookUserId);

			redirectUrl = "#/app/me/checkin";
		}

		if(foundFacebookCustomerDTO != null)
		{
			foundFacebookCustomerDTO.setLogged(true);
			lastMessagesService.checkCustomerOnline(foundFacebookCustomerDTO.getId());

			//			customerService.getEntityById(foundFacebookCustomer.getId());
			//			customerRepository.saveAndFlush(foundFacebookCustomer);

			if(foundFacebookCustomerDTO.getEmail()==null || foundFacebookCustomerDTO.getEmail().isEmpty())
			{
				foundFacebookCustomerDTO.setEmail("");
			}

			loginService.setLogged(foundFacebookCustomerDTO.getId(), true);
			//			try
			//			{
			//				Customer entity = customerService.getEntityById(foundFacebookCustomerDTO.getId());
			//			
			//				if(entity!=null){
			//					entity.setLogged(true);
			//					entity.updateLastSeenOnline();
			//					customerRepository.saveAndFlush(entity);
			//				}
			//			}
			//			catch (Error e)
			//			{
			//				;
			//			}
			session.setAttribute(ControllerUtils.SESSION_CUSTOMER_ID, foundFacebookCustomerDTO.getId());
			session.setAttribute(ControllerUtils.SESSION_CUSTOMER, foundFacebookCustomerDTO);

		}

		return new RedirectView(applicationHost+redirectUrl);
	}

	private AccessGrant getFacebookAccessGrant(String authorizationCode) {
		OAuth2Operations oauthOperations =
				facebookConnectionFactory.getOAuthOperations();

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		//		if (useParametersForClientAuthentication) {
		params.set("client_id", facebookClientId);
		params.set("client_secret", facebookClientSecret);
		//		}

		return oauthOperations.exchangeForAccess(authorizationCode,
				applicationHost + facebookCallBackPath, params);
	}

//	private AccessGrant getLinkedInAccessGrant(String authorizationCode) {
//		OAuth2Operations oauthLinkedInOperations =
//				linkedInConnectionFactory.getOAuthOperations();
//		return oauthLinkedInOperations.exchangeForAccess(authorizationCode,
//				applicationHost + linkedInCallBackPath, null);
//	}
//
//	private String getLinkedInUserId(Connection<LinkedIn> connection) {
//
//		ConnectionKey connectionKey = connection.getKey();
//		return connectionKey.getProviderUserId();
//	}

	private String getFacebookUserId(Connection<Facebook> connection) {

		ConnectionKey connectionKey = connection.getKey();
		return connectionKey.getProviderUserId();
	}
}
