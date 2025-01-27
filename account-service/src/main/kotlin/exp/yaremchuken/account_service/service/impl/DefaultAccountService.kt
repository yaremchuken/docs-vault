package exp.yaremchuken.account_service.service.impl

import exp.yaremchuken.account_service.repository.AccountCredentialsRepository
import exp.yaremchuken.account_service.repository.AccountRepository
import exp.yaremchuken.account_service.repository.AccountSettingsRepository
import exp.yaremchuken.account_service.repository.entity.Account
import exp.yaremchuken.account_service.repository.entity.AccountCredentials
import exp.yaremchuken.account_service.repository.entity.AccountSettings
import exp.yaremchuken.account_service.service.AccountService
import exp.yaremchuken.account_service.service.model.AccountWithSettings
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class DefaultAccountService(
    val accountRepository: AccountRepository,
    val accountCredentialsRepository: AccountCredentialsRepository,
    val accountSettingsRepository: AccountSettingsRepository
): AccountService {

    @Transactional
    override fun addAccount(
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

    override fun getAccount(id: Long): AccountWithSettings {
        val acc = accountRepository.findById(id).orElseThrow { NoSuchElementException() }
        val settings = accountSettingsRepository.findById(id).orElseThrow { NoSuchElementException() }

        return AccountWithSettings(acc, settings)
    }

    @Transactional
    override fun deleteAccount(id: Long) {
        accountCredentialsRepository.deleteById(id)
        accountSettingsRepository.deleteById(id)
        accountRepository.deleteById(id)
    }
}