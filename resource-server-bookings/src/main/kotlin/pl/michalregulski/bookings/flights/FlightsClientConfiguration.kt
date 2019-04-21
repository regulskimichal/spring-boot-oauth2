package pl.michalregulski.bookings.flights

import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails

@Configuration
class FlightsClientConfiguration(
        private val oAuth2ClientContext: OAuth2ClientContext
) {

    /**
     * This means that we inject an interceptor which, before a request is made, will extract an oauth2 access token from
     * the authenticated user context. If the access token cannot be extracted from the context the application will try
     * to get the token using the `authorization_code` grant, as defined by the second parameter, the [OAuth2AuthenticationDetails],
     * in this case it's [AuthorizationCodeResourceDetails]
     */
    @Bean
    fun oauth2FeignRequestInterceptor(): OAuth2FeignRequestInterceptor {
        return OAuth2FeignRequestInterceptor(oAuth2ClientContext, AuthorizationCodeResourceDetails())
    }
}
