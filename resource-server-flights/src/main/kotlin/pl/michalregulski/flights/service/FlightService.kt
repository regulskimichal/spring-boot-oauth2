package pl.michalregulski.flights.service

import pl.michalregulski.flights.model.Flight
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class FlightService {

    fun getAllFlights(): Collection<Flight> = FLIGHTS

    fun getFlightById(id: String): Flight? = FLIGHTS.firstOrNull { f -> f.id == id }

    companion object {
        private val FLIGHTS = listOf(
                Flight(
                        departureDate = Date.from(Instant.parse("2018-04-03T10:15:30.00Z")),
                        departure = "MAD",
                        arrivalDate = Date.from(Instant.parse("2018-04-03T11:05:30.00Z")),
                        destination = "AMS",
                        id = "1"
                ),
                Flight(
                        departureDate = Date.from(Instant.parse("2018-03-16T12:15:30.00Z")),
                        departure = "BER",
                        arrivalDate = Date.from(Instant.parse("2018-03-16T13:55:30.00Z")),
                        destination = "LON",
                        id = "2"
                )
        )
    }
}
