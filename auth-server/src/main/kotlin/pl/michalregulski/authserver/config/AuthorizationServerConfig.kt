package pl.michalregulski.authserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore

@Configuration
class AuthorizationServerConfig(
        private val authenticationManager: AuthenticationManager,
        private val customUserDetailsService: CustomUserDetailsService
) : AuthorizationServerConfigurerAdapter() {

    @Bean
    fun tokenStore(): TokenStore {
        return InMemoryTokenStore()
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(customUserDetailsService)
                .allowedTokenEndpointRequestMethods(GET, POST)
    }

    /**
     * This method defines how secured are operations related to getting a new token and validating an existing token.
     * According to this configuration, every resource server, when validating a token will need to authenticate himself.
     * This configuration is identical as adding the configuration parameters below:
     * `security.oauth2.authorization.checkTokenAccess: isAuthenticated()` and
     * `security.oauth2.authorization.tokenKeyAccess: permitAll()`
     */
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
                .withClient("acme")
                .secret("{noop}acmesecret")
                .accessTokenValiditySeconds(TOKEN_EXPIRATION_SECONDS)
                .scopes("read-flights", "read-bookings")
                .autoApprove()
                .redirectUris("http://localhost:8090/login")
                .authorizedGrantTypes("client_credentials", "authorization_code", "password", "refresh_token")
                .and()
                .withClient("bookings")
                .secret("{noop}bookingssecret")
                .and()
                .withClient("flights")
                .secret("{noop}flightssecret")
    }

    companion object {
        private const val TOKEN_EXPIRATION_SECONDS = 3600
    }
}
