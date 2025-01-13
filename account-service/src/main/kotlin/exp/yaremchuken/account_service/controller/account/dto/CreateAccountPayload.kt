package exp.yaremchuken.account_service.controller.account.dto

data class CreateAccountPayload(
    val email: String,
    val password: String,
    val username: String?
)
