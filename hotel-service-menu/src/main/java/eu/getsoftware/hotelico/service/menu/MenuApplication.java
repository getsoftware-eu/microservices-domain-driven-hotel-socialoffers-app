package eu.getsoftware.hotelico.service.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources( //in case of K8s: from shared-client-lib module
		@PropertySource("classpath:client-${spring.profiles.active}.properties") //use client-default.properties or client-k8s.properties für feign client properties!
)
public class MenuApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MenuApplication.class, args);
	}
}
