package pl.michalregulski.flights.model

import java.util.*

class Flight(
        val id: String,
        val departure: String,
        val destination: String,
        val departureDate: Date,
        val arrivalDate: Date
)
