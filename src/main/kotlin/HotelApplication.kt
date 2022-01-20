package de.hotelico;

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableCaching
class HotelApplication

fun main(args: Array<String>) {
    runApplication<HotelApplication>(*args)
}
