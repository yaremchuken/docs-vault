package exp.yaremchuken.vault_service.service.impl

import exp.yaremchuken.vault_service.repository.SecretGroupRepository
import exp.yaremchuken.vault_service.repository.SecretRepository
import exp.yaremchuken.vault_service.repository.entity.SecretGroup
import exp.yaremchuken.vault_service.service.SecretGroupService
import org.springframework.stereotype.Service

@Service
class DefaultSecretGroupService(
    val secretRepository: SecretRepository,
    val secretGroupRepository: SecretGroupRepository,
): SecretGroupService {

    override fun addGroupToAccount(accountId: Long, title: String): SecretGroup {
        return secretGroupRepository.save(
            SecretGroup(
                accountId = accountId,
                title = title
            )
        )
    }

    override fun renameGroup(groupId: Long, title: String): SecretGroup {
        return secretGroupRepository.updateTitleById(title, groupId)
    }

    override fun getGroup(groupId: Long): SecretGroup? {
        return secretGroupRepository.findById(groupId).orElse(null)
    }

    override fun getGroupsInfoByAccount(accountId: Long): Map<SecretGroup, Int> {
        val groups = secretGroupRepository.findByAccountId(accountId)
        val secrets = secretRepository.findByGroupIdIn(groups.map { it.id })

        return groups.associateWith { gr -> secrets.filter { it.groupId == gr.id}.size }
    }
}