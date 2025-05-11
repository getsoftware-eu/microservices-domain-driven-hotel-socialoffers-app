package eu.getsoftware.hotelico.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources

@SpringBootApplication //(scanBasePackages = {"eu.getsoftware.hotelico"})
@PropertySources(PropertySource("classpath:client-\${spring.profiles.active}.properties"))
@EnableCaching
class ChatApplication  
     
fun main(args: Array<String>) {
    runApplication<ChatApplication>(*args) {
    }
}
 
