package th.co.techman.api.domain.common.exception

import org.springframework.http.HttpStatus

class DataNotFoundException(private val errorMessage: String) : ApiMessageSourceException() {
    override val message: String? = errorMessage
    override fun getArguments() = arrayOf(errorMessage)
    override fun getCodes() = arrayOf("not_found.001")
    override var code = "DATA_NOT_FOUND_EXCEPTION"
    override var httpStatus = HttpStatus.BAD_REQUEST
}
