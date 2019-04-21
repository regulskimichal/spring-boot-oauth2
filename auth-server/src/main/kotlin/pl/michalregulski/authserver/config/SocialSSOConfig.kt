package pl.michalregulski.authserver.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter

import javax.servlet.Filter

@Configuration
class SocialSSOConfig(
        private val oAuth2ClientContext: OAuth2ClientContext,
        private val facebookClientConfig: FacebookClientConfig
) {

    @Bean
    fun oauth2ClientFilterRegistration(filter: OAuth2ClientContextFilter): FilterRegistrationBean<*> {
        val registration = FilterRegistrationBean(filter)
        registration.order = -100
        return registration
    }

    @Bean
    fun ssoSocialFilter(): Filter {
        val facebookFilter = OAuth2ClientAuthenticationProcessingFilter("/login/facebook")
        val restTemplate = OAuth2RestTemplate(facebookClientConfig.client!!, oAuth2ClientContext)
        facebookFilter.setRestTemplate(restTemplate)
        val facebookUserAuthenticationService = FacebookUserAuthenticationService(facebookClientConfig.client!!.clientId)
        facebookFilter.setTokenServices(facebookUserAuthenticationService)
        return facebookFilter
    }
}
