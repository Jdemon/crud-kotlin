package th.co.techman.api.infra.customer.repository.postgres.configuration

import org.springframework.data.domain.AuditorAware
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.Optional

class AuditorAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)
            ?.request
        val userId = request?.getHeader("x-user-id")
        return Optional.of(userId ?: "admin")
    }
}
