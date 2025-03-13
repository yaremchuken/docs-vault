package exp.yaremchuken.vault_service.controller.mapper

import exp.yaremchuken.vault_service.openapi.model.SecretData
import exp.yaremchuken.vault_service.openapi.model.SecretSecuredData
import exp.yaremchuken.vault_service.repository.entity.SecretVersion
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface SecretMapper {

    fun toSecretData(version: SecretVersion): SecretData

    @Mapping(source = "secretValue", target = "value")
    fun toSecuredData(version: SecretVersion): SecretSecuredData
}