package exp.yaremchuken.account_service.controller.account

import exp.yaremchuken.account_service.controller.account.mapper.AccountMapper
import exp.yaremchuken.account_service.controller.exception.ElementAlreadyExistsException
import exp.yaremchuken.account_service.openapi.api.AccountApi
import exp.yaremchuken.account_service.openapi.model.CreateAccountRequest
import exp.yaremchuken.account_service.openapi.model.GetAccountResponse
import exp.yaremchuken.account_service.service.impl.DefaultAccountService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.Locale

@RestController
class AccountController(
    val accountService: DefaultAccountService,
    val accountMapper: AccountMapper
): AccountApi {

    override fun createAccount(request: CreateAccountRequest): ResponseEntity<GetAccountResponse> {
        try {
            val acc = accountService
                .addAccount(
                    request.email,
                    request.password,
                    request.username,
                    Locale.of(request.locale)
                )

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountMapper.toGetAccountResponse(acc))
        } catch (ex: DataIntegrityViolationException) {
            throw ElementAlreadyExistsException("account.error.already.exists.title")
        }
    }

    override fun getAccount(accountId: Long): ResponseEntity<GetAccountResponse> {
        try {
            val acc = accountService.getAccount(accountId)
            return ResponseEntity.ok(accountMapper.toGetAccountResponse(acc))
        } catch (ex: NoSuchElementException) {
            throw NoSuchElementException("account.error.not.found.title")
        }
    }

    override fun deleteAccount(accountId: Long): ResponseEntity<Void> {
        accountService.deleteAccount(accountId)
        return ResponseEntity.noContent().build()
    }
}