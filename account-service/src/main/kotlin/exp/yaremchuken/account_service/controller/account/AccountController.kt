package exp.yaremchuken.account_service.controller.account

import exp.yaremchuken.account_service.controller.account.dto.CreateAccountPayload
import exp.yaremchuken.account_service.controller.account.dto.GetAccountResponse
import exp.yaremchuken.account_service.entity.Account
import exp.yaremchuken.account_service.repository.AccountRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(
    val accountRepository: AccountRepository
) {

    @GetMapping
    fun getAccount(@RequestParam id: Int, request: HttpServletRequest): ResponseEntity<GetAccountResponse> {
        val acc = accountRepository
            .findById(id)
            .map {
                GetAccountResponse(
                    id = it.id,
                    email = it.email,
                    username = it.username
                )
            }
            .orElseThrow {
                NoSuchElementException("account.404.title")
            }

        return ResponseEntity.ok(acc)
    }

    @PostMapping("create")
    fun createAccount(@Valid @RequestBody payload: CreateAccountPayload): ResponseEntity<GetAccountResponse> {
        val acc = accountRepository.save(
            Account(
                username = payload.username,
                password = payload.password,
                email = payload.email
            )
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                GetAccountResponse(
                    id = acc.id,
                    email = acc.email,
                    username = acc.username
                )
            )
    }
}