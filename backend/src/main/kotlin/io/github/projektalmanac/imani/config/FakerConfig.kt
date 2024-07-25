package io.github.projektalmanac.imani.config

import com.github.javafaker.Faker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class FakerConfig {
    @Bean
    fun faker() = Faker(Locale("es-MX"), Random(0))
}
