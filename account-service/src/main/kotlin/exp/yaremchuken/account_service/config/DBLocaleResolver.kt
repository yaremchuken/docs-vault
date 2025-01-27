package exp.yaremchuken.account_service.config

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import exp.yaremchuken.account_service.repository.AccountSettingsRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AbstractLocaleResolver
import java.util.Locale
import java.util.concurrent.TimeUnit

@Component
class DBLocaleResolver(
    val sessionLocaleResolver: LocaleResolver
): AbstractLocaleResolver() {

    @Autowired
    lateinit var accountSettingsRepository: AccountSettingsRepository

    private val cache: LoadingCache<Long, Locale> =
        CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(
                object : CacheLoader<Long, Locale>() {
                    override fun load(key: Long) =
                        accountSettingsRepository.findById(key).map { it.locale }.orElse(Locale.ENGLISH)
                }
            )

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val accountId = request.getHeader("X_ACCOUNT_ID")?.toLong()

        return if (accountId == null) {
            return sessionLocaleResolver.resolveLocale(request)
        } else {
            cache.get(accountId)
        }
    }

    override fun setLocale(request: HttpServletRequest, response: HttpServletResponse?, locale: Locale?) { }
}