package th.co.techman.api.infra.configuration

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile

@Import(RedisProfileConfiguration.WithDB::class, RedisProfileConfiguration.WithOutDB::class)
class RedisProfileConfiguration {
    @Profile("!dev")
    @Import(RedisConfiguration::class)
    class WithDB

    @Profile("dev")
    @EnableAutoConfiguration(
        exclude = [
            RedisAutoConfiguration::class,
            RedisRepositoriesAutoConfiguration::class
        ]
    )
    class WithOutDB
}
