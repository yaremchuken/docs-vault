package exp.yaremchuken.account_service.controller.account.dto

import java.util.Locale

data class CreateAccountPayload(
    val email: String,
    val password: String,
    val username: String?,
    val locale: Locale = Locale.ENGLISH
)
