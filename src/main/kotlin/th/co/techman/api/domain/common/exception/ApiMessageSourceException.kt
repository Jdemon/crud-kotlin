package th.co.techman.api.domain.common.exception

import org.springframework.context.MessageSourceResolvable

abstract class ApiMessageSourceException : BaseApiException(), MessageSourceResolvable
