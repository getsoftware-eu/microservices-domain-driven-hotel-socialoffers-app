package eu.getsoftware.hotelico.hotelapp.main.config.infrastructure

import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.FILTER_PATH_MULTITENANT
import eu.getsoftware.hotelico.hotelapp.main.config.infrastructure.resolver.MultitenantKeycloakConfigResolver
import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents
import org.keycloak.adapters.springsecurity.authentication.KeycloakLogoutHandler
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy


@Configuration
@EnableWebSecurity // - disables boot!
@ComponentScan(basePackageClasses = [KeycloakSecurityComponents::class])
@ConditionalOnProperty(value = ["keycloak.enabled"], matchIfMissing = true)
class AuthKeycloakConfig : KeycloakWebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var keycloakClientRequestFactory: KeycloakClientRequestFactory

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Throws(Exception::class)
    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder ){

        val keycloakAuthenticationProvider = keycloakAuthenticationProvider()

        /**
         * By default in Spring Security, roles are prefixed with ROLE_. We could change that in our Realm configuration but it could be confusing for other applications that do not know this convention, so here we assign a SimpleAuthorityMapper that will make sure no prefix is added.
         * */
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())

        auth.authenticationProvider(keycloakAuthenticationProvider)
    }


    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    override fun sessionAuthenticationStrategy() : SessionAuthenticationStrategy {

        /**
         * You must provide a session authentication strategy bean which should be of type RegisterSessionAuthenticationStrategy for public or confidential applications and NullAuthenticatedSessionStrategy for bearer-only applications.
         * */

        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun keycloakRestTemplate(): KeycloakRestTemplate {
        /**
         * ResponseEntity<String[]> response = template.getForEntity(endpoint, String[].class);
         * */
        return KeycloakRestTemplate(keycloakClientRequestFactory)
    }

    /**
     * Spring Boot attempts to eagerly register filter beans with the web application context. Therefore, when running the Keycloak Spring Security adapter in a Spring Boot environment, it may be necessary to add two FilterRegistrationBeans to your security configuration to prevent the Keycloak filters from being registered twice.
     */
    @Bean
    fun keycloakAuthenticationProcessingFilterRegistrationBean(filter : KeycloakAuthenticationProcessingFilter): FilterRegistrationBean<*> {
        var registrationBean = FilterRegistrationBean(filter)
        registrationBean.isEnabled = false
        return registrationBean
    }

    @Bean
    fun keycloakPreAuthActionsFilterRegistrationBean(filter: KeycloakPreAuthActionsFilter): FilterRegistrationBean<*> {
        var registrationBean = FilterRegistrationBean(filter)
        registrationBean.isEnabled = false
        return registrationBean
    }

    @Throws(Exception::class)
    @Override
    override fun keycloakLogoutHandler() : KeycloakLogoutHandler {
        return super.keycloakLogoutHandler()
    }

    @Throws(Exception::class)
    @Override
    override fun configure(http: HttpSecurity) {

        super.configure(http)

        http
                .authorizeRequests()
                .antMatchers("/sso/login*").permitAll()
                .antMatchers("/login*").hasRole("user")
                .antMatchers("/$FILTER_PATH_MULTITENANT/**/login*").hasRole("user") // activates keycloak interceptor on url
                .antMatchers("/login/*").hasRole("user")
                .anyRequest().permitAll()

        http.csrf().disable()
    }

    @Bean
    fun keycloakConfigResolver(): KeycloakConfigResolver {
        return MultitenantKeycloakConfigResolver()
    }
}