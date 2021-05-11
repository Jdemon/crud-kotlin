package th.co.techman.api.infra.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties(prefix = "redis.cache")
class CacheTtlConfiguration {
    lateinit var customerTtl: Duration
}
