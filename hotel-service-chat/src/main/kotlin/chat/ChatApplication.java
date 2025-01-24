package chat;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication//(scanBasePackages = {"eu.getsoftware.hotelico"})
//@EnableEurekaClient
//@EnableFeignClients(basePackages = "eu.getsoftware.hotelico.clients")
@PropertySources( //in case of K8s: from shared-client-lib module
		@PropertySource("classpath:client-${spring.profiles.active}.properties") //use client-default.properties or client-k8s.properties für feign client properties!
)
@EnableCaching
public class ChatApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ChatApplication.class, args);
	}
}
