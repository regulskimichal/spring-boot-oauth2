package pl.michalregulski.bookings

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ResourceServerBookingsApplication

fun main(args: Array<String>) {
    runApplication<ResourceServerBookingsApplication>(*args)
}

