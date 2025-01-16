package exp.yaremchuken.account_service.service

import exp.yaremchuken.account_service.repository.AccountCredentialsRepository
import exp.yaremchuken.account_service.repository.AccountRepository
import exp.yaremchuken.account_service.repository.AccountSettingsRepository
import exp.yaremchuken.account_service.repository.model.Account
import exp.yaremchuken.account_service.repository.model.AccountCredentials
import exp.yaremchuken.account_service.repository.model.AccountSettings
import exp.yaremchuken.account_service.service.model.AccountWithSettings
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class AccountService(
    val accountRepository: AccountRepository,
    val accountCredentialsRepository: AccountCredentialsRepository,
    val accountSettingsRepository: AccountSettingsRepository
) {
    @Transactional
    fun addAccount(
        email: String,
        password: String,
        username: String?,
        locale: Locale
    ): AccountWithSettings {
        val acc = accountRepository
            .save(
                Account(
                    email = email,
                    username = username
                )
            )

        val credentials = accountCredentialsRepository.save(AccountCredentials(acc.id, password))
        val settings = accountSettingsRepository.save(AccountSettings(acc.id, locale))

        return AccountWithSettings(acc, settings)
    }

    fun getAccount(id: Int): AccountWithSettings {
        val acc = accountRepository.findById(id).orElseThrow { NoSuchElementException() }
        val settings = accountSettingsRepository.findById(id).orElseThrow { NoSuchElementException() }

        return AccountWithSettings(acc, settings)
    }
}