package exp.yaremchuken.vault_service.controller

import exp.yaremchuken.vault_service.controller.mapper.SecretMapper
import exp.yaremchuken.vault_service.openapi.api.SecretApi
import exp.yaremchuken.vault_service.openapi.model.CreateSecretRequest
import exp.yaremchuken.vault_service.openapi.model.ListSecretsResponse
import exp.yaremchuken.vault_service.openapi.model.RenewSecretRequest
import exp.yaremchuken.vault_service.openapi.model.SecretData
import exp.yaremchuken.vault_service.openapi.model.SecretSecuredData
import exp.yaremchuken.vault_service.service.SecretService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SecretController(
    val secretService: SecretService,
    val secretMapper: SecretMapper
): SecretApi {

    override fun createSecret(createSecretRequest: CreateSecretRequest): ResponseEntity<SecretData> {
        val secret = secretService.addSecret(
            groupId = createSecretRequest.groupId,
            title = createSecretRequest.title,
            secretValue = createSecretRequest.value
        )
        return ResponseEntity.ok(secretMapper.toSecretData(secret))
    }

    override fun renewSecret(renewSecretRequest: RenewSecretRequest): ResponseEntity<SecretData> {
        val secret = secretService.renewSecret(
            secretId = renewSecretRequest.secretId,
            title = renewSecretRequest.title,
            secretValue = renewSecretRequest.value
        )
        return ResponseEntity.ok(secretMapper.toSecretData(secret))
    }

    override fun listSecretVersions(accountId: Long, secretId: Long): ResponseEntity<ListSecretsResponse> {
        val versions = secretService.getSecretVersions(secretId).map { secretMapper.toSecretData(it) }
        return ResponseEntity.ok(ListSecretsResponse(versions))
    }

    override fun getSecuredValue(accountId: Long, secretId: Long, versionId: Long): ResponseEntity<SecretSecuredData> {
        val versionSecret = secretService.getSecretValue(versionId)
        return ResponseEntity.ok(secretMapper.toSecuredData(versionSecret))
    }

    override fun listSecretsInGroup(accountId: Long, groupId: Long): ResponseEntity<ListSecretsResponse> {
        val latestVersionsInGroup = secretService.getSecretsInGroup(groupId).map { secretMapper.toSecretData(it) }
        return ResponseEntity.ok(ListSecretsResponse(latestVersionsInGroup))
    }
}