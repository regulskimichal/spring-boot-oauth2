package pl.michalregulski.flights

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ResourceServerFlightsApplication

fun main(args: Array<String>) {
    runApplication<ResourceServerFlightsApplication>(*args)
}
