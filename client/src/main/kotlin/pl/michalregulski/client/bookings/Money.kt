package pl.michalregulski.client.bookings

import java.math.BigDecimal

data class Money(
        val amount: BigDecimal?,
        val currencyCode: String?
)
