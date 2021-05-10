package th.co.techman.api.infra.configuration

import org.dozer.DozerBeanMapper
import org.dozer.Mapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {
    @Bean
    fun dozerBeanMapper(): Mapper? {
        return DozerBeanMapper()
    }
}
