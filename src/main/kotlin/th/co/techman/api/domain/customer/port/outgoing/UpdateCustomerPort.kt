package th.co.techman.api.domain.customer.port.outgoing

import th.co.techman.api.domain.customer.model.Customer

interface UpdateCustomerPort {
    fun updateCustomer(customer: Customer): Customer
}