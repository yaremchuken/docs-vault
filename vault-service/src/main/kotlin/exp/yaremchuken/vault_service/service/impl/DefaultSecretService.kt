package exp.yaremchuken.vault_service.service.impl

import exp.yaremchuken.vault_service.repository.SecretRepository
import exp.yaremchuken.vault_service.repository.SecretVersionRepository
import exp.yaremchuken.vault_service.repository.entity.Secret
import exp.yaremchuken.vault_service.repository.entity.SecretVersion
import exp.yaremchuken.vault_service.service.SecretService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DefaultSecretService(
    val secretRepository: SecretRepository,
    val secretVersionRepository: SecretVersionRepository,
): SecretService {

    @Transactional
    override fun addSecret(groupId: Long, title: String, secretValue: String): SecretVersion {
        val secret = secretRepository.save(
            Secret(
                groupId = groupId
            )
        )

        return secretVersionRepository.save(
            SecretVersion(
                secretId = secret.id,
                title = title,
                secretValue = secretValue
            )
        )
    }

    override fun getSecretsInGroup(groupId: Long): Collection<SecretVersion> {
        val secrets = secretRepository.findByGroupIdIn(listOf(groupId))
        val versions = secretVersionRepository.findBySecretIdIn(secrets.map { it.id })

        return versions.groupBy { it.secretId }.mapValues { it.value.maxBy { v -> v.createdAt } }.values
    }

    override fun getSecretVersions(secretId: Long): Collection<SecretVersion> {
        return secretVersionRepository.findBySecretIdIn(listOf(secretId))
    }

    override fun renewSecret(secretId: Long, title: String, secretValue: String): SecretVersion {
        return secretVersionRepository.save(
            SecretVersion(
                secretId = secretId,
                title = title,
                secretValue = secretValue
            )
        )
    }

    override fun getSecretValue(versionId: Long): SecretVersion {
        return secretVersionRepository.findById(versionId).orElseThrow()
    }
}