package th.co.techman.api.infra.customer.repository.postgres

import org.dozer.Mapper
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository
import th.co.techman.api.domain.customer.model.Customer
import th.co.techman.api.domain.customer.port.outgoing.DeleteCustomerPort
import th.co.techman.api.domain.customer.port.outgoing.GetCustomerPort
import th.co.techman.api.domain.customer.port.outgoing.SaveCustomerPort
import th.co.techman.api.domain.customer.port.outgoing.UpdateCustomerPort
import th.co.techman.api.infra.configuration.CacheConfiguration
import th.co.techman.api.infra.customer.repository.postgres.entity.CustomerDetail
import th.co.techman.api.infra.customer.repository.postgres.repository.CustomerRepository

@Repository
class PostgresCustomerRepository(
    val customerRepository: CustomerRepository,
    val mapper: Mapper
) : GetCustomerPort, SaveCustomerPort, UpdateCustomerPort, DeleteCustomerPort {

    override fun getCustomer(): List<Customer> {
        val customerEntities: MutableList<Customer> = mutableListOf()
        customerRepository.findAll().forEach {
            val customer = Customer()
            mapper.map(it, customer)
            customerEntities.add(customer)
        }
        return customerEntities
    }

    @Cacheable(
        value = [CacheConfiguration.CUSTOMER],
        unless = "#result==null",
        cacheManager = "cacheManager",
        key = "#id"
    )
    override fun getCustomer(id: Long): Customer {
        val customerDetail = customerRepository.findById(id)
        val customer = Customer()
        mapper.map(customerDetail.get(), customer)
        return customer
    }

    override fun exist(id: Long) = customerRepository.existsById(id)

    override fun saveCustomer(customer: Customer) = saveOrUpdate(customer)

    @CachePut(
        value = [CacheConfiguration.CUSTOMER],
        unless = "#result==null",
        cacheManager = "cacheManager",
        key = "#customer.id"
    )
    override fun updateCustomer(customer: Customer) = saveOrUpdate(customer)

    @CacheEvict(
        value = [CacheConfiguration.CUSTOMER],
        cacheManager = "cacheManager",
        key = "#id"
    )
    override fun deleteCustomer(id: Long) = customerRepository.deleteById(id)

    private fun saveOrUpdate(customer: Customer): Customer {
        var customerDetail = CustomerDetail()
        mapper.map(customer, customerDetail)
        customerDetail = customerRepository.save(customerDetail)
        mapper.map(customerDetail, customer)
        return customer
    }
}
