package th.co.techman.api.infra.configuration

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.lettuce.core.ReadFrom
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableCaching
class RedisConfiguration {

    @Autowired
    lateinit var redisProperties: RedisProperties

    @Primary
    @Bean(name = ["cacheRedisConnectionFactory"])
    @ConditionalOnBean(RedisProfileConfiguration.WithDB::class)
    fun redisConnectionFactory(): RedisConnectionFactory {
        return when {
            isClusterMode() -> {
                val clientConfig = LettuceClientConfiguration.builder()
                    .readFrom(ReadFrom.REPLICA_PREFERRED)
                    .build()
                val redisClusterConfiguration = RedisClusterConfiguration(redisProperties.cluster.nodes)
                redisClusterConfiguration.password = RedisPassword.of(redisProperties.password)
                LettuceConnectionFactory(redisClusterConfiguration, clientConfig)
            }
            else -> {
                val standaloneConfiguration = RedisStandaloneConfiguration(redisProperties.host, redisProperties.port)
                standaloneConfiguration.database = redisProperties.database
                standaloneConfiguration.password = RedisPassword.of(redisProperties.password)
                val clientConfig = JedisClientConfiguration.builder().build()
                JedisConnectionFactory(standaloneConfiguration, clientConfig)
            }
        }
    }

    @Bean
    fun objectMapper(): ObjectMapper = jacksonObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)

    @Bean(name = ["cacheRedisTemplate"])
    @ConditionalOnBean(RedisProfileConfiguration.WithDB::class)
    fun redisTemplate(
        objectMapper: ObjectMapper,
        redisConnectionFactory: RedisConnectionFactory
    ): RedisTemplate<Any, Any> {
        val redisTemplate = RedisTemplate<Any, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory)
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.setDefaultSerializer(GenericJackson2JsonRedisSerializer(objectMapper))
        redisTemplate.hashKeySerializer = GenericJackson2JsonRedisSerializer(objectMapper)
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)
        return redisTemplate
    }

    private fun isClusterMode(): Boolean {
        return redisProperties.cluster?.nodes?.isNotEmpty() ?: false
    }
}
