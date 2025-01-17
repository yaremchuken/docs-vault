package exp.yaremchuken.account_service.controller.account.dto

data class GetAccountResponse(
    val id: Int,
    val email: String,
    val username: String?,
    val confirmed: Boolean,
    val locale: String
)
