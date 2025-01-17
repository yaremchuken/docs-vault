package exp.yaremchuken.account_service.controller.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.LocaleResolver

@ControllerAdvice
class GlobalExceptionHandler(
    val messageSource: MessageSource,
    val localeResolver: LocaleResolver
) {

    @ExceptionHandler(Exception::class)
    fun globalHandler(exception: Exception, request: HttpServletRequest) =
        ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ProblemDetail
                    .forStatusAndDetail(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        getMessage(exception, "common.error.internal.title", request)
                    )
            )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationHandler(ex: MethodArgumentNotValidException) =
        ResponseEntity(
            ex.bindingResult.allErrors.associate {
                (it as FieldError).field to it.defaultMessage
            }.toMutableMap().also {
                it["has_validation_errors"] = "true"
            },
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementHandler(exception: NoSuchElementException, request: HttpServletRequest) =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ProblemDetail
                    .forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        getMessage(exception, "common.error.not.found.title", request)
                    )
            )

    @ExceptionHandler(ElementAlreadyExistsException::class)
    fun elementAlreadyExistsHandler(exception: ElementAlreadyExistsException, request: HttpServletRequest ) =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ProblemDetail
                    .forStatusAndDetail(
                        HttpStatus.BAD_REQUEST,
                        getMessage(exception, "common.error.already.exists.title", request)
                    )
            )

    private fun getMessage(ex: Exception, default: String, request: HttpServletRequest) =
        messageSource
            .getMessage(
                ex.message ?: default,
                null,
                ex.message ?: default,
                localeResolver.resolveLocale(request)
            )
}