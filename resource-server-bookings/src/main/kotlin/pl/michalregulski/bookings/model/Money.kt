package pl.michalregulski.bookings.model

import java.math.BigDecimal

data class Money(
        var amount: BigDecimal?,
        var currencyCode: String?
)
