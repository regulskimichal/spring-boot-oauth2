package pl.michalregulski.client.config

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableOAuth2Sso
class SecurityConfig : WebSecurityConfigurerAdapter() {

    public override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/api/unsecure/**").permitAll()
                .antMatchers("/**").authenticated()
    }

}
