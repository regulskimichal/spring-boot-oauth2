package pl.michalregulski.bookings.model

import pl.michalregulski.bookings.flights.Flight
import java.util.*

data class Booking(
        var id: String?,
        var creationDate: Date?,
        var price: Money?,
        var flight: Flight?,
        var airline: String?
)
