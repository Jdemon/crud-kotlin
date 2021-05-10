package th.co.techman.api.app

import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.toMessage
import th.co.techman.api.app.base.ApiErrorResponse
import th.co.techman.api.domain.common.exception.ApiMessageSourceException
import th.co.techman.api.domain.common.exception.DataNotFoundException
import java.util.Locale

@RestControllerAdvice
class ApiExceptionHandler(
    private val messageSource: MessageSource
) : ResponseEntityExceptionHandler() {

    companion object {
        const val defaultErrorMessage = "Internal Server Error"
        const val INTERNAL_ERROR = "INTERNAL_ERROR"
        const val CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION"
        const val messageBundle = "messages"
    }

    @ExceptionHandler(value = [ApiMessageSourceException::class])
    fun handleApiMessageException(
        e: DataNotFoundException,
        locale: Locale
    ): ResponseEntity<ApiErrorResponse> {
        logger.error("Error Occurred with message: ${e.message}")
        val response = ApiErrorResponse.error(e.code, messageSource.getMessage(e, locale))
        return ResponseEntity(response, e.httpStatus)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun handleConstraintViolationException(
        e: ConstraintViolationException,
        locale: Locale
    ): ResponseEntity<ApiErrorResponse> {
        logger.error(CONSTRAINT_VIOLATION, e)
        val message =
            e.constraintViolations.joinToString { it.property.plus(": ").plus(it.toMessage(messageBundle).message) }
        val response = ApiErrorResponse.error(CONSTRAINT_VIOLATION, message)
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleRuntimeException(e: Exception): ResponseEntity<ApiErrorResponse> {
        logger.error("Error Occurred with message: ${e.cause}", e)
        val response = ApiErrorResponse.error(INTERNAL_ERROR, e.message ?: defaultErrorMessage)
        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
