package exp.yaremchuken.vault_service.repository

import exp.yaremchuken.vault_service.repository.entity.Secret
import org.springframework.data.repository.CrudRepository

interface SecretRepository: CrudRepository<Secret, Long> {

    fun findByGroupIdIn(groupIds: Collection<Long>): Collection<Secret>
}