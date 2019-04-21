package pl.michalregulski.bookings.service

import org.springframework.stereotype.Service
import pl.michalregulski.bookings.flights.Flight
import pl.michalregulski.bookings.flights.FlightsClient
import pl.michalregulski.bookings.model.Booking
import pl.michalregulski.bookings.model.Money
import java.math.BigDecimal
import java.util.*

@Service
class BookingService(
        private val flightsClient: FlightsClient
) {

    fun getAllBookings(): Collection<Booking> {
        return BOOKINGS
                .map { it.copy(flight = flightsClient.getById(it.flight?.id!!)) }
    }

    fun getBookingById(id: String): Booking? {
        return BOOKINGS
                .filter { it.id == id }
                .map { it.copy(flight = flightsClient.getById(it.flight?.id!!)) }
                .firstOrNull()
    }

    companion object {
        private val BOOKINGS = listOf(
                Booking(
                        creationDate = Date(),
                        flight = Flight(id = "1"),
                        id = "1",
                        price = Money(BigDecimal.valueOf(130.0), "EUR"),
                        airline = "KLM"
                ),
                Booking(
                        creationDate = Date(),
                        flight = Flight(id = "2"),
                        id = "2",
                        price = Money(BigDecimal.valueOf(70.0), "EUR"),
                        airline = "Lufthansa"
                ))
    }
}
