package eu.getsoftware.hotelico.hotelapp.main;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"eu.getsoftware.hotelico"})
//@EnableEurekaClient
//@EnableFeignClients(basePackages = "eu.getsoftware.hotelico.clients")
//PropertySources( //in case of K8s
//  @PropertySource("classpath:clients-${spring.profiles.active}.properties") //use client-default.properties or client-k8s.properties für feign client properties!
//})
@EnableCaching
public class MainApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MainApplication.class, args);
	}
}
