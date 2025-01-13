package exp.yaremchuken.account_service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver

@Configuration
class AppConfig {

    @Bean
    fun localeResolver(): LocaleResolver = SessionLocaleResolver()
}