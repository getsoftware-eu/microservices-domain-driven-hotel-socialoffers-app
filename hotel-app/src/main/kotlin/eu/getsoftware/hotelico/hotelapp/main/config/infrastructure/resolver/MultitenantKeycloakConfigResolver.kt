package eu.getsoftware.hotelico.hotelapp.main.config.infrastructure.resolver

import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.FILTER_PATH_MULTITENANT
import eu.getsoftware.hotelico.clients.common.utils.AppConfigProperties.PARAM_DEFAULT_REALM
import org.keycloak.adapters.KeycloakDeployment
import org.keycloak.adapters.KeycloakDeploymentBuilder
import org.keycloak.adapters.spi.HttpFacade
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.representations.adapters.config.AdapterConfig
import java.util.concurrent.ConcurrentHashMap

/**
 *
 * <br/>
 * Created by e.fanshil
 * At 03.04.2018 17:25
 */
open class MultitenantKeycloakConfigResolver() : KeycloakSpringBootConfigResolver()
{
    private val cache = ConcurrentHashMap<String, KeycloakDeployment>()
    
    private val ignoreSubPaths = listOf("sso/login", "webjars/", "fonts/", "css/", "js/", "/favicon")

    private var useKeycloakBootAdapterConfig = true

    private val defaultRealm = "default"
    
    private val lastUsedCustomRealm = "lastUsedCustomRealm"

    override fun resolve(request: HttpFacade.Request): KeycloakDeployment {

        val path = request.uri
        val multitenantIndex = path.indexOf("$FILTER_PATH_MULTITENANT/")

        // prevent, that default customRealm occurs again, because of standard requests
        if(ignoreSubPaths.filter{ ignoreSubPath -> path.contains(ignoreSubPath) }.isNotEmpty() && cache.containsKey("lastUsedCustomRealm"))
        {
            return cache[lastUsedCustomRealm]!!
        }

        // default realm
        if (multitenantIndex == -1) {

            cache.remove(lastUsedCustomRealm)

            var keycloakDevelopment = cache[defaultRealm]   
             
            if(null == keycloakDevelopment)
            {
                keycloakDevelopment = getCreateKeycloakDeployment(PARAM_DEFAULT_REALM, request)
                cache[defaultRealm] = keycloakDevelopment
            }
            
            return keycloakDevelopment
        }

        var customRealm = path.substring(path.indexOf("$FILTER_PATH_MULTITENANT/")).split("/")[1]
        if (customRealm.contains("?")) {
            customRealm = customRealm.split("\\?")[0]
        }

        var keycloakDeployment: KeycloakDeployment? = cache[customRealm]
        
        if(null == keycloakDeployment) {
            keycloakDeployment = getCreateKeycloakDeployment(customRealm, request)
            cache[customRealm] = keycloakDeployment
        }
        
        // save last customRealm
        cache[lastUsedCustomRealm] = keycloakDeployment!!

        return keycloakDeployment!!
    }

    private fun getCreateKeycloakDeployment(realmName: String, request: HttpFacade.Request): KeycloakDeployment {
       
        if (!useKeycloakBootAdapterConfig)
        {
            val inputStream = javaClass.getResourceAsStream("/keycloak/${realmName.toLowerCase()}-keycloak.json") ?: throw IllegalStateException("Not able to find the file /static/$realmName-keycloak.json")
            return KeycloakDeploymentBuilder.build(inputStream)
        }
            
        if(m_adapterConfig == null /*|| realmName == defaultRealm*/)
        {
            return super.resolve(request)
        }
             
        if (m_adapterConfig?.realm != realmName) {
            m_adapterConfig?.realm = realmName
        }
                
        return KeycloakDeploymentBuilder.build(m_adapterConfig)
    }

    companion object {
        
        var m_adapterConfig: AdapterConfig? = null
        
        internal fun setAdapterConfig(adapterConfig: AdapterConfig) {
            m_adapterConfig = adapterConfig
        }    
    }

}