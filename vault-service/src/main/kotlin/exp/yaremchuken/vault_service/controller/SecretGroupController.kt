package exp.yaremchuken.vault_service.controller

import exp.yaremchuken.vault_service.controller.mapper.SecretGroupMapper
import exp.yaremchuken.vault_service.openapi.api.SecretGroupApi
import exp.yaremchuken.vault_service.openapi.model.CreateGroupRequest
import exp.yaremchuken.vault_service.openapi.model.GetSecretGroupsResponse
import exp.yaremchuken.vault_service.openapi.model.SecretGroupInfo
import exp.yaremchuken.vault_service.service.SecretGroupService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SecretGroupController(
    val secretGroupService: SecretGroupService,
    val secretGroupMapper: SecretGroupMapper
): SecretGroupApi {

    override fun createGroup(
        accountId: Long,
        createGroupRequest: CreateGroupRequest
    ): ResponseEntity<SecretGroupInfo> {
        val group = secretGroupService.addGroupToAccount(accountId, createGroupRequest.title)
        return ResponseEntity.ok(secretGroupMapper.mapSecretGroup(group, 0))
    }

    override fun listGroups(accountId: Long): ResponseEntity<GetSecretGroupsResponse> {
        val groups = secretGroupService.getGroupsInfoByAccount(accountId)
        val groupInfos = groups.entries.map { secretGroupMapper.mapSecretGroup(it.key, it.value) }

        return ResponseEntity.ok(GetSecretGroupsResponse(groupInfos))
    }
}