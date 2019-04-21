package pl.michalregulski.flights.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.michalregulski.flights.model.Flight
import pl.michalregulski.flights.service.FlightService

@RestController
@RequestMapping("/api/flights/v1")
class FlightsController(
        private val flightService: FlightService
) {

    @GetMapping
    fun getAllFlights(): ResponseEntity<Collection<Flight>> {
        logSecurityData()
        return ResponseEntity.ok(flightService.getAllFlights())
    }

    @GetMapping("/{id}")
    fun getFlightById(@PathVariable("id") id: String): ResponseEntity<Flight> {
        logSecurityData()
        return when (val flightById = flightService.getFlightById(id)) {
            null -> ResponseEntity.notFound().build()
            else -> ResponseEntity.ok(flightById)
        }
    }

    private fun logSecurityData() {
        val authenticationData = SecurityContextHolder.getContext().authentication as OAuth2Authentication
        val authenticationDataDetails = authenticationData.details as OAuth2AuthenticationDetails

        log.debug("Flights logged in client token: '{}'", authenticationDataDetails.tokenValue)
        log.debug("Flights logged in client id: '{}'", authenticationData.oAuth2Request.clientId)
        log.debug("Flights logged in client scopes: '{}'", authenticationData.oAuth2Request.scope)

        log.debug("Flights logged in client from principal: '{}'", authenticationData.principal)
        log.debug("Flights logged in client authorities: '{}'", authenticationData.authorities)
    }

    companion object {
        private val log = LoggerFactory.getLogger(FlightsController::class.java)
    }
}
