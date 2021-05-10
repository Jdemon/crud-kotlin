package th.co.techman.api.infra.customer.repository.postgres.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableTransactionManagement
class JpaConfiguration {
    @Bean
    fun auditorAware(): AuditorAware<*> = AuditorAwareImpl()
}