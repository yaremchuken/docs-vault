package exp.yaremchuken.vault_service.service

import exp.yaremchuken.vault_service.repository.entity.SecretGroup

interface SecretGroupService {

    fun addGroupToAccount(accountId: Long, title: String): SecretGroup

    fun renameGroup(groupId: Long, title: String): SecretGroup

    fun getGroup(groupId: Long): SecretGroup?

    fun getGroupsInfoByAccount(accountId: Long): Map<SecretGroup, Int>
}
