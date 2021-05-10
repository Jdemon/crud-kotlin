package th.co.techman.api.domain.common.exception

import org.springframework.http.HttpStatus

abstract class BaseApiException : RuntimeException() {
    abstract var code: String
    abstract var httpStatus: HttpStatus
    open var data: Any? = null
}
