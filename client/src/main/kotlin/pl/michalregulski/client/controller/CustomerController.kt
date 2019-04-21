package pl.michalregulski.client.controller

import pl.michalregulski.client.model.Customer
import pl.michalregulski.client.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customer/v1")
class CustomerController(
        private val customerService: CustomerService
) {

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable("id") customerId: String): ResponseEntity<Customer> {
        val customer = customerService.getById(customerId)
        return when {
            customer != null -> ResponseEntity.ok(customer)
            else -> ResponseEntity.notFound().build()
        }
    }

}
