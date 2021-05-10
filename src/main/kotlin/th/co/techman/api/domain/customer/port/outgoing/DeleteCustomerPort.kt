package th.co.techman.api.domain.customer.port.outgoing

interface DeleteCustomerPort {
    fun deleteCustomer(id: Long)
}
