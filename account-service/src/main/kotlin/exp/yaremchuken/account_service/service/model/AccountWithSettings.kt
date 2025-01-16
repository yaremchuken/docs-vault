package exp.yaremchuken.account_service.service.model

import exp.yaremchuken.account_service.repository.model.Account
import exp.yaremchuken.account_service.repository.model.AccountSettings

data class AccountWithSettings(
    val account: Account,
    val settings: AccountSettings
)
