package pl.michalregulski.authserver.config

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails
import org.springframework.stereotype.Component

@ConfigurationProperties("facebook")
@Component
class FacebookClientConfig {

    var client: AuthorizationCodeResourceDetails? = null

    var resource: ResourceServerProperties? = null

}
