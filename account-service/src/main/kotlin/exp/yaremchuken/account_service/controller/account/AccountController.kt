package exp.yaremchuken.account_service.controller.account

import exp.yaremchuken.account_service.controller.account.dto.CreateAccountPayload
import exp.yaremchuken.account_service.controller.account.dto.GetAccountResponse
import exp.yaremchuken.account_service.controller.account.mapper.AccountMapper
import exp.yaremchuken.account_service.controller.exception.ElementAlreadyExistsException
import exp.yaremchuken.account_service.service.impl.DefaultAccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Locale

@Validated
@RestController
@RequestMapping("account")
class AccountController(
    val accountService: DefaultAccountService,
    val accountMapper: AccountMapper
) {

    @Operation(
        summary = "Get Account by identifier"
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Account fetched successfully"
        ),
        ApiResponse(
            responseCode = "404",
            description = "Account with such identifier doesn't exists",
            content = [ Content(schema = Schema(implementation = ProblemDetail::class)) ]
        )
    )
    @GetMapping("{id:\\d+}")
    fun getAccount(@PathVariable id: Int): ResponseEntity<GetAccountResponse> {
        try {
            val acc = accountService.getAccount(id)
            return ResponseEntity.ok(accountMapper.toGetAccountResponse(acc))
        } catch (ex: NoSuchElementException) {
            throw NoSuchElementException("account.error.not.found.title")
        }
    }

    @Operation(
        summary = "Create Account"
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            description = "Account successfully created"
        ),
        ApiResponse(
            responseCode = "400",
            description = "Account with such credentials already exists",
            content = [ Content(schema = Schema(implementation = ProblemDetail::class)) ]
        )
    )
    @PostMapping("create")
    fun createAccount(@Valid @RequestBody payload: CreateAccountPayload): ResponseEntity<GetAccountResponse> {
        try {
            val acc = accountService
                .addAccount(
                    payload.email,
                    payload.password,
                    payload.username,
                    Locale.of(payload.locale)
                )

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountMapper.toGetAccountResponse(acc))
        } catch (ex: DataIntegrityViolationException) {
            throw ElementAlreadyExistsException("account.error.already.exists.title")
        }
    }

    @Operation(
        summary = "Delete Account"
    )
    @ApiResponse(
        responseCode = "204",
        description = "Account successfully deleted or doesn't exists"
    )
    @DeleteMapping("delete/{id:\\d+}")
    fun deleteAccount(@PathVariable id: Int): ResponseEntity<Void> {
        accountService.deleteAccount(id)
        return ResponseEntity.noContent().build()
    }
}