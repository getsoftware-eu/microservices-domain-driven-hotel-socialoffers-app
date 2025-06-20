//package eu.getsoftware.hotelico.service.booking.main.config.infrastructure; 
////
////import org.springframework.context.annotation.Configuration;
////import org.springframework.core.env.Environment;
////import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
////import org.springframework.social.config.annotation.SocialConfigurer;
////import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
////
////@Configuration
////public class SocialConfig implements SocialConfigurer
////{
////
////	@Override
////	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
////		cfConfig.addConnectionFactory(new LinkedInConnectionFactory(
////				env.getProperty("77rxbh8n0ta2fc"),
////				env.getProperty("aW3L9r9racGYIV5I")));
////	}
////}
//
///*
// * Copyright 2014 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//		/*
// * Copyright 2014 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.social.SecurityContext;
//import eu.getsoftware.hotelico.service.booking.application.hotel.domain.infrastructure.social.SimpleConnectionSignUp;
//import eu.getsoftware.hotelico.service.booking.main.config.adapter.SimpleSignInAdapter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.core.env.Environment;
//import org.springframework.security.crypto.encrypt.Encryptors;
//import org.springframework.social.UserIdSource;
//import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
//import org.springframework.social.config.annotation.EnableSocial;
//import org.springframework.social.config.annotation.SocialConfigurer;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.ConnectionRepository;
//import org.springframework.social.connect.UsersConnectionRepository;
//import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
//import org.springframework.social.facebook.api.Facebook;
//import org.springframework.social.facebook.connect.FacebookConnectionFactory;
//import org.springframework.social.facebook.web.CanvasSignInController;
//
//import javax.sql.DataSource;
////		import org.springframework.social.linkedin.api.LinkedIn;
////		import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
////		import org.springframework.social.quickstart.user.SecurityContext;
////		import org.springframework.social.quickstart.user.SimpleConnectionSignUp;
////		import org.springframework.social.quickstart.user.SimpleSignInAdapter;
//
///**
// * Spring Social Configuration.
// * @author Craig Walls
// */
//@Configuration
//@EnableSocial
//public class SocialConfig implements SocialConfigurer {
//
//	private DataSource dataSource;
//
//	//
//	// SocialConfigurer implementation methods
//	//
//
//
//	private static final String facebookClientSecret = "f492cdd7e0d9aee36ce8446c5caa440d";
//
//	private static final String facebookClientId = "383626641847356";
//	
//	@Override
//	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
//		cfConfig.addConnectionFactory(new FacebookConnectionFactory(facebookClientId, facebookClientSecret));
//	}
//
//	@Bean
//	@Scope(value="request", proxyMode= ScopedProxyMode.INTERFACES)
//	public Facebook facebook(ConnectionRepository repository) {
//		Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
//		return connection != null ? connection.getApi() : null;
//	}
//
//	/**
//	 * Singleton data access object providing access to connections across all users.
//	 */
//	@Override
//	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
//		repository.setConnectionSignUp(new SimpleConnectionSignUp());
//		return repository;
//	}
//
//	public UserIdSource getUserIdSource() {
//		return new UserIdSource() {
//			@Override
//			public String getUserId() {
//				return SecurityContext.getCurrentCustomer().getId()+"";
//			}
//		};
//	}
//
////	@Bean
////	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
////	public LinkedIn linkedIn(ConnectionRepository repository) {
////		Connection<LinkedIn> connection = repository.findPrimaryConnection(LinkedIn.class);
////		return connection != null ? connection.getApi() : null;
////	}
//
//	@Bean
//	public CanvasSignInController canvasSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, Environment env) {
//		return new CanvasSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter(), facebookClientId, facebookClientSecret, "http://apps.facebook.com/springsocialcanvas");
//	}
//
//}
