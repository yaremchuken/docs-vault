package exp.yaremchuken.account_service.service

import exp.yaremchuken.account_service.service.model.AccountWithSettings
import java.util.Locale

interface AccountService {

    fun addAccount(email: String, password: String, username: String?, locale: Locale): AccountWithSettings

    fun getAccount(id: Long): AccountWithSettings

    fun deleteAccount(id: Long)
}