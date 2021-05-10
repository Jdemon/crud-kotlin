package th.co.techman.api.domain.customer.port.outgoing

import th.co.techman.api.domain.customer.model.Customer

interface GetCustomerPort {
    fun getCustomer(): List<Customer>
    fun getCustomer(id: Long): Customer
}
