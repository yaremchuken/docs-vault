package exp.yaremchuken.vault_service.repository

import exp.yaremchuken.vault_service.repository.entity.SecretVersion
import org.springframework.data.repository.CrudRepository

interface SecretVersionRepository: CrudRepository<SecretVersion, Long> {
    fun findBySecretIdIn(secretIds: Collection<Long>): Collection<SecretVersion>
}
