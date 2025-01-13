package exp.yaremchuken.account_service.repository

import exp.yaremchuken.account_service.entity.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: CrudRepository<Account, Int>