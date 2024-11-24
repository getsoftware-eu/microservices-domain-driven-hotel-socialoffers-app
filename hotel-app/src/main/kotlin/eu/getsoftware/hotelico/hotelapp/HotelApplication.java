package eu.getsoftware.hotelico.hotelapp;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(scanBasePackages = {"eu.getsoftware.hotelico"})
//PropertySources( //in case of K8s
//  @PropertySource("classpath:clients-${spring.profiles.active}.properties") //use client-default.properties or client-k8s.properties für feign client properties!
//})
//@EnableCaching
public class HotelApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(HotelApplication.class, args);
	}
}
