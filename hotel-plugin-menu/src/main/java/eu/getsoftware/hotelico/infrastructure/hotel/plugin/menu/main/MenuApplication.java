package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class MenuApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MenuApplication.class, args);
	}
}
