package pl.michalregulski.bookings.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.michalregulski.bookings.model.Booking
import pl.michalregulski.bookings.service.BookingService

@RestController
@RequestMapping("/api/bookings/v1")
class BookingsController(
        private val bookingService: BookingService
) {

    @GetMapping
    fun getAllBookings(): ResponseEntity<Collection<Booking>> {
        logSecurityData()
        return ResponseEntity.ok(bookingService.getAllBookings())
    }

    @GetMapping("/{id}")
    fun getBookingById(@PathVariable("id") id: String): ResponseEntity<Booking> {
        logSecurityData()
        val bookingById = bookingService.getBookingById(id)
        return if (bookingById == null) {
            ResponseEntity.notFound().build()
        } else ResponseEntity.ok(bookingById)
    }

    private fun logSecurityData() {
        val authenticationData = SecurityContextHolder.getContext().authentication as OAuth2Authentication
        val authenticationDataDetails = authenticationData.details as OAuth2AuthenticationDetails

        log.debug("Bookings logged in client token: '{}'", authenticationDataDetails.tokenValue)
        log.debug("Bookings logged in client id: '{}'", authenticationData.oAuth2Request.clientId)
        log.debug("Bookings logged in client scopes: '{}'", authenticationData.oAuth2Request.scope)

        log.debug("Bookings logged in client from principal: '{}'", authenticationData.principal)
        log.debug("Bookings logged in client authorities: '{}'", authenticationData.authorities)
    }

    companion object {
        private val log = LoggerFactory.getLogger(BookingsController::class.java)
    }
}
