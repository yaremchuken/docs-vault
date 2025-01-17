package exp.yaremchuken.account_service.controller.account.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.util.Locale

data class CreateAccountPayload(

    @field:NotEmpty(message = "{account.create.restriction.email_is_empty}")
    @field:Email(message = "{account.create.restriction.email_is_invalid}")
    val email: String,

    @field:NotEmpty(message = "{account.create.restriction.password_is_empty}")
    @field:Size(message = "{account.create.restriction.password_size}", min = 3, max = 8)
    val password: String,

    @field:Size(message = "{account.create.restriction.username_size}", min = 3, max = 50)
    val username: String?,

    val locale: String = Locale.ENGLISH.toString()
)
