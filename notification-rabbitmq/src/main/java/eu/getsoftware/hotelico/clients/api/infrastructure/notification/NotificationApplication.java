package eu.getsoftware.hotelico.clients.api.infrastructure.notification;

import eu.getsoftware.hotelico.clients.api.amqp.producer.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    
    /**
     * CommandLineRunner - local testing a Bean
     * @param producer
     * @param rabbitMQProducerConfig
     * @return
     */
    @Bean
    CommandLineRunner commandLineRunner(
            RabbitMQMessageProducer producer,
            RabbitMQProducerConfig rabbitMQProducerConfig
            ) {
        return args -> {
            //eugen2022: send via ampq model, not directly!!! hiding aggregate!!
            producer.publish( //Object payload, String exchange, String routingKey
                    rabbitMQProducerConfig.getInternalExchangeName(),
                    rabbitMQProducerConfig.getInternalNotificationRoutingKeyName(),
                    new Person("Ali", 18));
        };
    }

    record Person(String name, int age){}
}
