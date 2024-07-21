package io.github.projektalmanac.imani.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChan(http: HttpSecurity): SecurityFilterChain =
        http.authorizeHttpRequests {
            it.anyRequest().permitAll()
        }.build()
}
