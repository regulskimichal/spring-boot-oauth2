package pl.michalregulski.bookings.flights

import feign.hystrix.FallbackFactory
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET

@FeignClient(name = "\${service.flights-api.service-name}", url = "\${service.flights-api.service-address}", fallbackFactory = FlightsClientFallbackFactory::class, configuration = [FlightsClientConfiguration::class])
interface FlightsClient {

    @RequestMapping(method = [GET], value = ["/"])
    fun getAll(): Collection<Flight>

    @RequestMapping(method = [GET], value = ["/{id}"])
    fun getById(@PathVariable("id") id: String): Flight?

}

@Component
internal class FlightsClientFallbackFactory : FallbackFactory<FlightsClient> {

    override fun create(cause: Throwable): FlightsClient {
        return object : FlightsClient {
            override fun getAll(): Collection<Flight> {
                log.debug("Fallback for request to get all flights, triggered because of following reason: '{}'", cause.message)
                log.error("Exception occurred while getting all flights", cause)
                return emptyList()
            }

            override fun getById(id: String): Flight? {
                log.debug("Fallback for request to get flight by id: '{}', triggered because of following reason: '{}'", id, cause.message)
                log.error("Exception occurred while getting flight", cause)
                return null
            }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(FlightsClientFallbackFactory::class.java)
    }
}
