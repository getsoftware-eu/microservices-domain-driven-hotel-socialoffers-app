package eu.getsoftware.hotelico.notification;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import eu.getsoftware.hotelico.amqp.RabbitMQMessageProducer;

@SpringBootApplication(
        scanBasePackages = {
                "eu.getsoftware.hotelico.notification",
                "eu.getsoftware.hotelico.amqp", //eugen in order to use @RabbitListener(queues)
        }
)
//@EnableEurekaClient
//@EnableFeignClients(basePackages = "eu.getsoftware.hotelico.clients")
//PropertySources( //in case of K8s
//  @PropertySource("classpath:clients-${spring.profiles.active}.properties") //use client-default.properties or client-k8s.properties für feign client properties!
//})
public class NotificationApplication
{
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            RabbitMQMessageProducer producer,
            NotificationConfig notificationConfig
            ) {
        return args -> {
            //eugen2022: send via ampq model, not directly!!! hiding aggregate!!
            producer.publish( //Object payload, String exchange, String routingKey
                    new Person("Ali", 18),
                    notificationConfig.getInternalExchangeName(),
                    notificationConfig.getInternalNotificationRoutingKeyName());
        };
    }

    record Person(String name, int age){}
}
