package pl.michalregulski.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ClientApplication

fun main(args: Array<String>) {
    runApplication<ClientApplication>(*args)
}
