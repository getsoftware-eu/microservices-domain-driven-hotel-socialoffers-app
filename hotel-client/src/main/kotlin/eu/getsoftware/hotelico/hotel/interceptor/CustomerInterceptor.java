package eu.getsoftware.hotelico.hotel.interceptor;

//import org.springframework.social.linkedin.api.LinkedIn;
//import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class CustomerInterceptor extends HandlerInterceptorAdapter
{
////	@Override
////	public boolean preHandle(HttpServletRequest request,
////			HttpServletResponse response, Object handler) throws Exception
////	{
////		String uri = request.getRequestURI();
////		if(uri.endsWith("linkedIn"))
////		{
////			LinkedInConnectionFactory connectionFactory =
////					new LinkedInConnectionFactory("77rxbh8n0ta2fc", "aW3L9r9racGYIV5I");
////			OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
////			OAuth2Parameters params = new OAuth2Parameters();
////			params.setRedirectUri("http://hotelico.de/hotelico/linkedIn/auth");
////			params.setState("login");
////			String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);
////			response.sendRedirect(authorizeUrl);
////			
////		}
////		return true;
////	}
//
////	private final UsersConnectionRepository connectionRepository;
//
//	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();
//
////	public CustomerInterceptor(UsersConnectionRepository connectionRepository) {
////		this.connectionRepository = connectionRepository;
////	}
//	
////	public CustomerInterceptor() {
//////		this.connectionRepository = connectionRepository;
////	}
//
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		rememberUser(request, response);
//		handleSignOut(request, response);
//		if (SecurityContext.userSignedIn() || requestForSignIn(request)) {
//			return true;
//		} else {
//			return requireSignIn(request, response);
//		}
//	}
//
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		SecurityContext.remove();
//	}
//
//	// internal helpers
//
//	private void rememberUser(HttpServletRequest request, HttpServletResponse response) {
//		String userId = userCookieGenerator.readCookieValue(request);
//		if (userId == null) {
//			return;
//		}
//		if (!userNotFound(userId)) {
//			userCookieGenerator.removeCookie(response);
//			return;
//		}
//		SecurityContext.setCurrentCustomer(new Customer(userId));
//	}
//
//	private void handleSignOut(HttpServletRequest request, HttpServletResponse response) {
//		if (SecurityContext.userSignedIn() && request.getServletPath().startsWith("/signout")) {
////			connectionRepository.createConnectionRepository(SecurityContext.getCurrentCustomer().getLinkedInId()).removeConnections("linkedIn");
//			userCookieGenerator.removeCookie(response);
//			SecurityContext.remove();
//		}
//	}
//
//	private boolean requestForSignIn(HttpServletRequest request) {
//		return request.getServletPath().startsWith("/signin");
//	}
//
//	private boolean requireSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		//new RedirectView("/signin", true).render(null, request, response);
//		
//		String uri = request.getRequestURI();
////		if(uri.endsWith("linkedIn"))
////		{
////			LinkedInConnectionFactory connectionFactory =
////					new LinkedInConnectionFactory("77rxbh8n0ta2fc", "aW3L9r9racGYIV5I");
////			OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
////			OAuth2Parameters params = new OAuth2Parameters();
////			params.setRedirectUri("http://hotelico.de/hotelico/linkedIn/auth");
////			params.setState("login");
////
////			String authorizeUrl = oauthOperations.buildAuthorizeUrl(params);
////			//					String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, params);
////
////			response.sendRedirect(authorizeUrl);
////			return false;
////		}
//		
//		return true;
//	}
//
//	private boolean userNotFound(String userId) {
//		// doesn't bother checking a local user database: simply checks if the userId is connected to Facebook
////		return connectionRepository.createConnectionRepository(userId).findPrimaryConnection(LinkedIn.class) != null;
//		return true;
//	}
}