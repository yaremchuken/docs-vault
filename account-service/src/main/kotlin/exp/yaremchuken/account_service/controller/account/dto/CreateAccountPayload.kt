package exp.yaremchuken.account_service.controller.account.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.util.Locale

@Schema(
    name = "Payload for Account creation",
    description = "Schema to hold Account creation credentials"
)
data class CreateAccountPayload(

    @Schema(
        description = "Account e-mail written in the standard form, unique for every account",
        example = "person@gmail.com"
    )
    @field:NotEmpty(message = "{account.create.restriction.email_is_empty}")
    @field:Email(message = "{account.create.restriction.email_is_invalid}")
    val email: String,

    @Schema(
        description = "Account password"
    )
    @field:NotEmpty(message = "{account.create.restriction.password_is_empty}")
    @field:Size(message = "{account.create.restriction.password_size}", min = 3, max = 8)
    val password: String,

    @Schema(
        description = "Account username, not unique",
        example = "person"
    )
    @field:Size(message = "{account.create.restriction.username_size}", min = 3, max = 50)
    val username: String?,

    @Schema(
        description = "Language preferred by Account, short form",
        example = "en"
    )
    val locale: String = Locale.ENGLISH.toString()
)
