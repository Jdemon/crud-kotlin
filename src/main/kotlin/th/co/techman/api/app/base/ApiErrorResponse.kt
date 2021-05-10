package th.co.techman.api.app.base

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import java.time.format.DateTimeFormatter

class ApiErrorResponse {

    @JsonProperty("timestamp")
    var timestamp: String? = null

    @JsonProperty("errorCode")
    var error: String? = null

    @JsonProperty("errorMessage")
    var message: String? = null

    private constructor(timestamp: String?, error: String?, message: String?) {
        this.timestamp = timestamp
        this.error = error
        this.message = message
    }

    companion object {
        fun error(code: String, message: String): ApiErrorResponse {
            val timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now())
            return ApiErrorResponse(timestamp, code, message)
        }
    }
}
