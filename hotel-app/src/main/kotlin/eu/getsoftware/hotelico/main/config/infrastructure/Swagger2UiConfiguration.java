package eu.getsoftware.hotelico.main.config.infrastructure;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * swagger docs will be visible here:
 * http://localhost:8080/api/v2/api-docs
 */

@Configuration
@EnableSwagger2
public class Swagger2UiConfiguration extends WebMvcConfigurerAdapter
{
	@Bean
	public Docket api() {
		// @formatter:off
		//Register the controllers to swagger
		//Also it is configuring the Swagger Docket
		return new Docket(DocumentationType.SWAGGER_2).select()
				// .apis(RequestHandlerSelectors.any())
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				// .paths(PathSelectors.any())
				// .paths(PathSelectors.ant("/swagger2-demo"))
				.build();
		// @formatter:on
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		//enabling swagger-ui part for visual documentation
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	/**
	 * we can allow Swagger to access an OAuth-secured API using the Authorization Code grant type
	 * 
	 * http://localhost:8082/spring-security-oauth-resource/swagger-ui.html
	 * with KEYKLOAK AUTHORISATION, in order to VIEW Swagger!
	 */
	
	private String AUTH_SERVER = "Keycloak";
	private String CLIENT_ID = "CLIENT_ID";
	private String CLIENT_SECRET = "CLIENT_SECRET";
	
	private SecurityScheme securityScheme() {
		GrantType grantType = new AuthorizationCodeGrantBuilder()
				.tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
				.tokenRequestEndpoint(
						new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_SECRET))
				.build();
		
		SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
				.grantTypes(Arrays.asList(grantType))
				.scopes(Arrays.asList(scopes()))
				.build();
		return oauth;
	}
	
	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = {
				new AuthorizationScope("read", "for read operations"),
				new AuthorizationScope("write", "for write operations"),
				new AuthorizationScope("foo", "Access foo API") };
		return scopes;
	}
}