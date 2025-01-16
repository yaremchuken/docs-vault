package exp.yaremchuken.account_service.service.model

import exp.yaremchuken.account_service.repository.entity.Account
import exp.yaremchuken.account_service.repository.entity.AccountSettings

data class AccountWithSettings(
    val account: Account,
    val settings: AccountSettings
)
