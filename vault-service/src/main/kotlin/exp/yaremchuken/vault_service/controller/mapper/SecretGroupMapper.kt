package exp.yaremchuken.vault_service.controller.mapper

import exp.yaremchuken.vault_service.openapi.model.SecretGroupInfo
import exp.yaremchuken.vault_service.repository.entity.SecretGroup
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface SecretGroupMapper {
    fun mapSecretGroup(group: SecretGroup, amount: Int): SecretGroupInfo
}