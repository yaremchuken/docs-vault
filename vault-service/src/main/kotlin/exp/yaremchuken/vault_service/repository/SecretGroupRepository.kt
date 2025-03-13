package exp.yaremchuken.vault_service.repository

import exp.yaremchuken.vault_service.repository.entity.SecretGroup
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SecretGroupRepository: CrudRepository<SecretGroup, Long> {

    @Query("update SecretGroup set title = :title where id = :id")
    fun updateTitleById(title: String, id: Long): SecretGroup

    fun findByAccountId(accountId: Long): Collection<SecretGroup>
}