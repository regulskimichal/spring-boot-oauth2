package pl.michalregulski.bookings.client.flights

import java.util.*

data class Flight(
        var id: String?,
        var departure: String? = null,
        var destination: String? = null,
        var departureDate: Date? = null,
        var arrivalDate: Date? = null
)
