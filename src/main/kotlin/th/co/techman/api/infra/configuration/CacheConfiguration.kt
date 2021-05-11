package th.co.techman.api.infra.configuration

import com.google.common.base.Joiner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration
import javax.annotation.PostConstruct

@Configuration
@EnableConfigurationProperties(value = [CacheTtlConfiguration::class])
@EnableCaching
class CacheConfiguration : CachingConfigurerSupport() {

    @Autowired
    lateinit var cacheTtlConfiguration: CacheTtlConfiguration

    @Autowired
    lateinit var redisConnectionFactory: RedisConnectionFactory

    companion object {
        const val CUSTOMER = "customer"
        const val CUSTOMER_ALL = "customer-all"
    }

    lateinit var cacheMap: Map<String, Duration>

    @PostConstruct
    fun init() {
        cacheMap = mapOf(
            CUSTOMER to cacheTtlConfiguration.customerTtl,
            CUSTOMER_ALL to cacheTtlConfiguration.customerAllTtl
        )
    }

    @Bean
    fun cacheManager(redisCacheConfiguration: RedisCacheConfiguration) = RedisCacheManager
        .builder(redisConnectionFactory)
        .initialCacheNames(cacheMap.keys)
        .withInitialCacheConfigurations(cacheMap.mapValues { redisCacheConfiguration.entryTtl(it.value) })
        .build()

    @Bean
    fun redisCacheConfiguration() = RedisCacheConfiguration.defaultCacheConfig()
        .serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer<Any>(
                GenericJackson2JsonRedisSerializer()
            )
        )

    @Bean
    override fun keyGenerator(): KeyGenerator {
        return KeyGenerator { target, method, params ->
            return@KeyGenerator "%s.%s(%s)".format(
                target::class.qualifiedName,
                method.name,
                Joiner.on(",").skipNulls().join(params)
            )
        }
    }
}
