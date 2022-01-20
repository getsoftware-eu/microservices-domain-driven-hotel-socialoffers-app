package de.hotelico.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.resource.locator.factory.ConfigurableLocatorFactory;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;
import ro.isdc.wro.model.resource.support.hash.ConfigurableHashStrategy;
import ro.isdc.wro.model.resource.support.naming.ConfigurableNamingStrategy;
import java.util.Properties;
/**
 * <br/>
 * Created by e.fanshil
 * At 03.11.2015 18:33
 */
@Configuration
//@EnableConfigurationProperties(Wro4jProperties.class)
public class Wro4jConfig {
	
	@Bean
	ConfigurableWroFilter wroFilter() {
		ConfigurableWroFilter wroFilter = new ConfigurableWroFilter();
		wroFilter.setProperties(wroFilterProperties());
		//		wroFilter.setWroManagerFactory(wroManagerFactory);
		return wroFilter;
	}
	
	Properties wroFilterProperties() {
		Properties properties = new Properties();
		properties.put(ConfigConstants.debug.name(), String.valueOf(true));
		properties.put(ConfigConstants.disableCache.name(), String.valueOf(false));
		properties.put(ConfigConstants.cacheUpdatePeriod, String.valueOf(0));
		properties.put(ConfigConstants.resourceWatcherUpdatePeriod, String.valueOf(0));
		properties.put(ConfigConstants.cacheGzippedContent, String.valueOf(true));
		properties.put(ConfigConstants.parallelPreprocessing, String.valueOf(true));
		return properties;
	}
	
//	
//	
////	public final static String WRO_FILTER_BEAN_NAME = "wroFilter";
////	
////	/**
////	 * Create the wro filter.
////	 *
////	 * @return
////	 */
////	@Bean(name = WRO_FILTER_BEAN_NAME)
////	public ConfigurableWroFilter wroFilter(WroManagerFactory wroManagerFactory, WroConfiguration wroConfiguration) {
////		ConfigurableWroFilter filter = new ConfigurableWroFilter();
////		filter.setConfiguration(wroConfiguration);
////		filter.setWroManagerFactory(wroManagerFactory);
////		return configureFilter(filter);
////	}
////	
////	protected ConfigurableWroFilter configureFilter(ConfigurableWroFilter filter) {
////		return filter;
////	}
////	
////	/**
////	 *
////	 * @return
////	 */
////	@Bean
////	public WroConfiguration wroConfiguration() {
////		WroConfiguration configuration = new WroConfiguration();
////		configuration.setDebug( true );
////		
////		configuration.setEncoding("UTF-8");
////		configuration.setIgnoreMissingResources(false);
////		configuration.setIgnoreFailingProcessor(false);
////		
////		if(isDevelopment()) {
////			configuration.setResourceWatcherUpdatePeriod(1l);
////			configuration.setCacheUpdatePeriod(3600);
//////			configuration.setHeader( org.sevensource.wro4spring.support.CachingHeaders.NO_CACHE_HEADERS );
////		} else {
//////			configuration.setHeader( NEVER_EXPIRES_HEADERS );
////		}
////		return configureConfiguration(configuration);
////	}
////	
//
//	/////////////////////////////////////////////////////////////////////////////
//	
//	
////	@Bean
////	FilterRegistrationBean wro4jFilterRegistration(ConfigurableWroFilter wroFilter) {
////		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(wroFilter);
////		filterRegistrationBean.addUrlPatterns(wro4jProperties.getUrlPattern());
////		return filterRegistrationBean;
////	}
////	
////	@ConditionalOnClass(groovy.lang.GroovyObject.class)
////	@ConditionalOnMissingBean(WroManagerFactory.class)
////	@Bean
////	WroManagerFactory groovyWroManagerFactory() {
////		return new GroovyWroManagerFactory(wro4jProperties.getGroovyResourceName(), wroManagerFactoryProperties());
////	}
////	
////	@ConditionalOnMissingBean(WroManagerFactory.class)
////	@Bean
////	WroManagerFactory fallbackWroManagerFactory() {
////		return new SimpleWroManagerFactory(wroManagerFactoryProperties());
////	}
////	
////	Properties wroManagerFactoryProperties() {
////		Properties properties = new Properties();
////		properties.put(ConfigurableLocatorFactory.PARAM_URI_LOCATORS, wro4jProperties.getUriLocators());
////		properties.put(ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS, wro4jProperties.getPreProcessors());
////		properties.put(ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS, wro4jProperties.getPostProcessors());
////		properties.put(ConfigurableNamingStrategy.KEY, wro4jProperties.getNamingStrategy());
////		properties.put(ConfigurableHashStrategy.KEY, wro4jProperties.getHashStrategy());
////		return properties;
////	}
//	
}