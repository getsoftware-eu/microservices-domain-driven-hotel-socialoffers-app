package eu.getsoftware.hotelico.config.wro4j;

import java.util.Properties;

import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;

/**
 * <br/>
 * Created by e.fanshil
 * At 03.11.2015 18:46
 */
public class SimpleWroManagerFactory extends ConfigurableWroManagerFactory
{
	private final Properties configProperties;
	
	public SimpleWroManagerFactory(Properties configProperties) {
		this.configProperties = configProperties;
	}
	
	@Override
	protected Properties newConfigProperties() {
		return configProperties;
	}
}
