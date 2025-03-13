package exp.yaremchuken.vault_service.service

import exp.yaremchuken.vault_service.repository.entity.SecretVersion

interface SecretService {

    fun addSecret(groupId: Long, title: String, secretValue: String): SecretVersion

    fun getSecretsInGroup(groupId: Long): Collection<SecretVersion>

    fun getSecretVersions(secretId: Long): Collection<SecretVersion>

    fun renewSecret(secretId: Long, title: String, secretValue: String): SecretVersion

    fun getSecretValue(versionId: Long): SecretVersion
}