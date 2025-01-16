package exp.yaremchuken.account_service.repository

import exp.yaremchuken.account_service.repository.model.AccountSettings
import org.springframework.data.repository.CrudRepository

interface AccountSettingsRepository: CrudRepository<AccountSettings, Int>