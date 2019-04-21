package pl.michalregulski.authserver.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
        private val customUserDetailsService: CustomUserDetailsService
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val email = authentication.name
        val password = authentication.credentials.toString()

        if ("admin" == email && "admin" == password) {
            val userDetails = customUserDetailsService.loadUserByUsername(email)
            val result = UsernamePasswordAuthenticationToken(userDetails, authentication.credentials, userDetails.authorities)
            result.details = authentication.details
            return result
        } else {
            throw UsernameNotFoundException("No internal user found with email: $email")
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

}
