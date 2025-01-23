package exp.yaremchuken.account_service.controller.account.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    name = "Account",
    description = "Schema to hold Account information"
)
data class GetAccountResponse(

    @Schema(
        description = "Unique Identifier for Account",
        example = "123"
    )
    val id: Int,

    @Schema(
        description = "Account e-mail written in the standard form, unique for every account",
        example = "person@gmail.com"
    )
    val email: String,

    @Schema(
        description = "Account username, not unique",
        example = "person"
    )
    val username: String?,

    @Schema(
        description = "Is account email confirmed by its creator",
        example = "true"
    )
    val confirmed: Boolean,

    @Schema(
        description = "Language preferred by Account, short form",
        example = "en"
    )
    val locale: String
)
