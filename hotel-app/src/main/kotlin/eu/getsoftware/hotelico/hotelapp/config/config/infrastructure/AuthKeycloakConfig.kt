import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.FILTER_PATH_MULTITENANT
import eu.getsoftware.hotelico.hotelapp.config.config.infrastructure.resolver.MultitenantKeycloakConfigResolver
import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springsecurity.KeycloakConfiguration
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider
import org.keycloak.adapters.springsecurity.authentication.KeycloakLogoutHandler
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy

@Configuration
@KeycloakConfiguration
@ConditionalOnProperty(value = ["keycloak.enabled"], matchIfMissing = true)
@ComponentScan(basePackageClasses = [KeycloakSecurityComponents::class])
class AuthKeycloakConfig {

    @Autowired
    private lateinit var keycloakClientRequestFactory: KeycloakClientRequestFactory

    @Bean
    fun keycloakConfigResolver(): KeycloakConfigResolver {
        return MultitenantKeycloakConfigResolver()
    }

    @Bean
    fun keycloakRestTemplate(): KeycloakRestTemplate {
        return KeycloakRestTemplate(keycloakClientRequestFactory)
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        val keycloakAuthenticationProvider = KeycloakAuthenticationProvider()
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(SimpleAuthorityMapper())
        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

    @Bean
    fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
    }

    @Bean
    fun keycloakAuthenticationProcessingFilterRegistrationBean(
        filter: KeycloakAuthenticationProcessingFilter
    ): FilterRegistrationBean<*> {
        val registrationBean = FilterRegistrationBean(filter)
        registrationBean.isEnabled = false
        return registrationBean
    }

    @Bean
    fun keycloakPreAuthActionsFilterRegistrationBean(
        filter: KeycloakPreAuthActionsFilter
    ): FilterRegistrationBean<*> {
        val registrationBean = FilterRegistrationBean(filter)
        registrationBean.isEnabled = false
        return registrationBean
    }

    @Bean
    fun filterChain(http: HttpSecurity, logoutHandler: KeycloakLogoutHandler): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it.requestMatchers("/sso/login*").permitAll()
                    .requestMatchers("/login*").hasRole("user")
                    .requestMatchers("/$FILTER_PATH_MULTITENANT/**/login*").hasRole("user")
                    .requestMatchers("/login/*").hasRole("user")
                    .anyRequest().permitAll()
            }
            .csrf { csrf ->
                csrf.disable()  // Новый способ отключения CSRF
            }
            .logout {
                it.addLogoutHandler(logoutHandler)  // Устанавливаем обработчик логаута
                    .logoutSuccessUrl("/") // Перенаправление после логаута            
             }

        return http.build()
    }
}
