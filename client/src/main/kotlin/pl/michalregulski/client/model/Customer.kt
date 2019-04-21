package pl.michalregulski.client.model

import pl.michalregulski.client.bookings.Booking

data class Customer(
        val id: String?,
        val firstName: String?,
        val lastName: String?,
        val email: String?,
        val booking: Booking?
)
