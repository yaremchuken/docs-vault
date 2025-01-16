package exp.yaremchuken.account_service.controller.account

import exp.yaremchuken.account_service.controller.account.dto.CreateAccountPayload
import exp.yaremchuken.account_service.controller.account.dto.GetAccountResponse
import exp.yaremchuken.account_service.service.AccountService
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
    val accountService: AccountService
) {

    @GetMapping
    fun getAccount(@RequestParam id: Int, request: HttpServletRequest): ResponseEntity<GetAccountResponse> {
        try {
            val acc = accountService.getAccount(id)
            return ResponseEntity.ok(
                GetAccountResponse(
                    id = acc.account.id,
                    email = acc.account.email,
                    username = acc.account.username,
                    locale = acc.settings.locale
                )
            )
        } catch (ex: NoSuchElementException) {
            throw NoSuchElementException("account.404.title")
        }
    }

    @PostMapping("create")
    fun createAccount(@Valid @RequestBody payload: CreateAccountPayload): ResponseEntity<GetAccountResponse> {
        val acc = accountService
            .addAccount(
                payload.email,
                payload.password,
                payload.username,
                payload.locale
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                GetAccountResponse(
                    id = acc.account.id,
                    email = acc.account.email,
                    username = acc.account.username,
                    locale = acc.settings.locale
                )
            )
    }
}