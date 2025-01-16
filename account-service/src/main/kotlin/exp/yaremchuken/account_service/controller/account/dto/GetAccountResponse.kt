package exp.yaremchuken.account_service.controller.account.dto

import java.util.Locale

data class GetAccountResponse(
    val id: Int,
    val email: String,
    val username: String?,
    val locale: Locale
)
