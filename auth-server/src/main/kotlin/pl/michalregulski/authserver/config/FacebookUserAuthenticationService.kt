package pl.michalregulski.authserver.config

import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
import org.springframework.social.facebook.api.Facebook
import org.springframework.social.facebook.api.User
import org.springframework.social.facebook.api.impl.FacebookTemplate
import java.util.*

class FacebookUserAuthenticationService(private val clientId: String) : ResourceServerTokenServices {

    override fun readAccessToken(accessToken: String): OAuth2AccessToken {
        log.error("Unsupported operation called")
        throw UnsupportedOperationException("Not supported: read access token")
    }

    @Throws(AuthenticationException::class, InvalidTokenException::class)
    override fun loadAuthentication(accessToken: String): OAuth2Authentication {
        log.debug("Creating authentication for facebook user from FB access token: '{}'", accessToken)

        val facebookConnection = FacebookTemplate(accessToken)

        val userProfile = getFacebookUserProfile(facebookConnection)
                .orElseThrow { RuntimeException("Failed to retrieve data from facebook auth provider") }
        log.debug("Found facebook user data: '{}'", userProfile)

        val email = getUserEmail(userProfile)
        val userId = getUserId(email)

        val authorities = Arrays.asList(
                SimpleGrantedAuthority("ROLE_USER"),
                SimpleGrantedAuthority("ROLE_FACEBOOK_USER"))

        val userAuthentication = UsernamePasswordAuthenticationToken(userId, NULL_CREDENTIALS, authorities)

        return OAuth2Authentication(constructOauthClientRequest(), userAuthentication)
    }

    private fun getFacebookUserProfile(facebook: Facebook): Optional<User> {
        log.debug("Retrieving user facebook profile based on facebook connection: '{}'", facebook)

        return Optional.ofNullable(facebook.fetchObject<User>(FACEBOOK_OBJECT_ID, User::class.java, *FACEBOOK_PROFILE_FIELDS))
    }

    private fun getUserEmail(facebookUserProfile: User): String {
        return Optional.ofNullable(facebookUserProfile.email)
                .filter { StringUtils.isNotBlank(it) }
                .orElseThrow { RuntimeException("Email was missing from user profile data") }
    }

    private fun getUserId(email: String): String {
        return "admin"
    }

    private fun constructOauthClientRequest(): OAuth2Request {
        return OAuth2Request(null, this.clientId, null, true, null, null, null, null, null)
    }

    companion object {

        private const val FACEBOOK_OBJECT_ID = "me"
        private val FACEBOOK_PROFILE_FIELDS = arrayOf("id", "email", "gender", "first_name", "last_name")
        private const val NULL_CREDENTIALS = "N/A"
        private val log = LoggerFactory.getLogger(FacebookUserAuthenticationService::class.java)
    }
}
