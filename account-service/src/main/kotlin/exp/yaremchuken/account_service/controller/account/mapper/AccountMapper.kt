package exp.yaremchuken.account_service.controller.account.mapper

import exp.yaremchuken.account_service.controller.account.dto.GetAccountResponse
import exp.yaremchuken.account_service.service.model.AccountWithSettings
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface AccountMapper {
    @Mapping(target = "id", source = "account.id")
    @Mapping(target = "email", source = "account.email")
    @Mapping(target = "username", source = "account.username")
    @Mapping(target = "confirmed", source = "account.confirmed")
    @Mapping(target = "locale", source = "settings.locale")
    fun toGetAccountResponse(account: AccountWithSettings): GetAccountResponse
}