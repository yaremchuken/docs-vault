package exp.yaremchuken.account_service.repository

import exp.yaremchuken.account_service.repository.entity.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository: CrudRepository<Account, Long>