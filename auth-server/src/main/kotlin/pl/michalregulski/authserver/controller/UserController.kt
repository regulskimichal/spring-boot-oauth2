package pl.michalregulski.authserver.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@RestController
class UserController {

    @RequestMapping("/user", "/me")
    fun user(principal: Principal): Principal {
        return principal
    }
}
