package pl.michalregulski.client.bookings

import java.util.*

data class Flight(
        val id: String,
        val departure: String,
        val destination: String,
        val departureDate: Date,
        val arrivalDate: Date
)
