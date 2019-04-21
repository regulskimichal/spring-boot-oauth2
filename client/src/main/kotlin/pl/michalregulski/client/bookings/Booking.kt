package pl.michalregulski.client.bookings

import java.util.*

data class Booking(
        val id: String? = null,
        val creationDate: Date? = null,
        val customerId: String? = null,
        val price: Money? = null,
        val flight: Flight? = null,
        val airline: String? = null
)
