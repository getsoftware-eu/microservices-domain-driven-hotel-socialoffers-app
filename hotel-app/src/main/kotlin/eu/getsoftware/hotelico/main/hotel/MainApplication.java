package eu.getsoftware.hotelico.main.hotel;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"eu.getsoftware.hotelico"})
//@EnableEurekaClient
//@EnableFeignClients(basePackages = "eu.getsoftware.hotelico.clients")
//PropertySources( //in case of K8s
//  @PropertySource("classpath:clients-${spring.profiles.active}.properties") //use client-default.properties or client-k8s.properties für feign client properties!
//})
public class MainApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MainApplication.class, args);
	}
}
