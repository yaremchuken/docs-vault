package exp.yaremchuken.account_service.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.LocaleResolver

@ControllerAdvice
class ExceptionHandler(
    val messageSource: MessageSource,
    val localeResolver: LocaleResolver
) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElement(
        exception: NoSuchElementException,
        request: HttpServletRequest
    ): ResponseEntity<ProblemDetail> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                ProblemDetail
                    .forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        messageSource
                            .getMessage(
                                exception.message ?: "common.404.title",
                                null,
                                "common.404.title",
                                localeResolver.resolveLocale(request)
                            ))
            )
}