package exp.yaremchuken.account_service.repository

import exp.yaremchuken.account_service.repository.model.AccountCredentials
import org.springframework.data.repository.CrudRepository

interface AccountCredentialsRepository: CrudRepository<AccountCredentials, Int>