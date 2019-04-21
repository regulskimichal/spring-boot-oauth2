package pl.michalregulski.client.service

import pl.michalregulski.client.bookings.Booking
import pl.michalregulski.client.bookings.BookingsClient
import pl.michalregulski.client.model.Customer
import org.springframework.stereotype.Service

@Service
class CustomerService(
        private val bookingsClient: BookingsClient
) {

    fun getById(customerId: String): Customer? {
        return CUSTOMERS
                .filter { it.id == customerId }
                .map { it.copy(booking = bookingsClient.getById(it.booking?.id!!)) }
                .firstOrNull()
    }

    companion object {
        private val CUSTOMERS = listOf(
                Customer(
                        id = "1",
                        email = "someemail@example.com",
                        firstName = "John",
                        lastName = "Person",
                        booking = Booking(id = "1")
                ),
                Customer(
                        id = "2",
                        email = "otheremail@example.com",
                        firstName = "Jack",
                        lastName = "Animal",
                        booking = Booking(id = "1")
                )
        )
    }
}
