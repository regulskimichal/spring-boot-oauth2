package pl.michalregulski.client.bookings

import feign.hystrix.FallbackFactory
import org.slf4j.LoggerFactory.getLogger
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
        name = "\${service.bookings-api.service-name}",
        url = "\${service.bookings-api.service-address}",
        fallbackFactory = BookingsClientFallbackFactory::class,
        configuration = [BookingsClientConfiguration::class]
)
@FunctionalInterface
interface BookingsClient {

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: String): Booking?

}

@Component
internal class BookingsClientFallbackFactory : FallbackFactory<BookingsClient> {

    private val log = getLogger(BookingsClientFallbackFactory::class.java)

    override fun create(cause: Throwable): BookingsClient {
        return object : BookingsClient {
            override fun getById(id: String): Booking? {
                log.debug("Fallback for request to get booking by id '{}', triggered because of following reason: '{}'", id, cause.message)
                log.error("Exception occurred while getting all bookings", cause)
                return null
            }
        }
    }
}
