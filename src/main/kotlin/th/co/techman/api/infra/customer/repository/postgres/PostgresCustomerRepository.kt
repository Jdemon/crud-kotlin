package th.co.techman.api.infra.customer.repository.postgres

import org.dozer.Mapper
import org.springframework.stereotype.Repository
import th.co.techman.api.domain.customer.model.Customer
import th.co.techman.api.domain.customer.port.outgoing.GetCustomerPort
import th.co.techman.api.infra.customer.repository.postgres.repository.CustomerRepository

@Repository
class PostgresCustomerRepository(
    val customerRepository: CustomerRepository,
    val mapper: Mapper
) : GetCustomerPort {

    override fun getCustomer(): List<Customer> {
        var customerEntities: MutableList<Customer> = mutableListOf()
        customerRepository.findAll().forEach {
            val customer = Customer()
            mapper.map(it , customer)
            customerEntities.add(customer);
        }
        return customerEntities;
    }

    override fun getCustomer(id: Long): Customer {
        val customerDetail = customerRepository.findById(id)
        val customer = Customer()
        mapper.map(customerDetail.get(), customer)
        return customer
    }
}