package th.co.techman.api.infra.customer.repository.postgres

import org.dozer.Mapper
import org.springframework.stereotype.Repository
import th.co.techman.api.domain.customer.model.Customer
import th.co.techman.api.domain.customer.port.outgoing.DeleteCustomerPort
import th.co.techman.api.domain.customer.port.outgoing.GetCustomerPort
import th.co.techman.api.domain.customer.port.outgoing.SaveCustomerPort
import th.co.techman.api.domain.customer.port.outgoing.UpdateCustomerPort
import th.co.techman.api.infra.customer.repository.postgres.entity.CustomerDetail
import th.co.techman.api.infra.customer.repository.postgres.repository.CustomerRepository
import javax.persistence.EntityNotFoundException

@Repository
class PostgresCustomerRepository(
    val customerRepository: CustomerRepository,
    val mapper: Mapper
) : GetCustomerPort, SaveCustomerPort, UpdateCustomerPort, DeleteCustomerPort {

    override fun getCustomer(): List<Customer> {
        var customerEntities: MutableList<Customer> = mutableListOf()
        customerRepository.findAll().forEach {
            val customer = Customer()
            mapper.map(it, customer)
            customerEntities.add(customer)
        }
        return customerEntities
    }

    override fun getCustomer(id: Long): Customer {
        val customerDetail = customerRepository.findById(id)
        val customer = Customer()
        mapper.map(customerDetail.get(), customer)
        return customer
    }

    override fun saveCustomer(customer: Customer) = saveOrUpdate(customer)

    override fun updateCustomer(customer: Customer): Customer {
        if (
            customer.id != null &&
            !customerRepository.existsById(customer.id!!)
        ) {
            throw EntityNotFoundException("No found customer.")
        }
        return saveOrUpdate(customer)
    }

    override fun deleteCustomer(id: Long) = customerRepository.deleteById(id)

    private fun saveOrUpdate(customer: Customer): Customer {
        var customerDetail = CustomerDetail()
        mapper.map(customer, customerDetail)
        customerDetail = customerRepository.save(customerDetail)
        mapper.map(customerDetail, customer)
        return customer
    }
}
