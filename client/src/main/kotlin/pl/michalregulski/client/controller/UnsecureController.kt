package pl.michalregulski.client.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UnsecureController {

    @GetMapping("/api/unsecure/info")
    fun info(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello world")
    }

}
