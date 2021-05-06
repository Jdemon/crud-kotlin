package th.co.techman.api.domain.customer.service

import org.springframework.stereotype.Service
import th.co.techman.api.domain.customer.model.Customer
import th.co.techman.api.domain.customer.port.incoming.GetCustomerUseCase
import th.co.techman.api.domain.customer.port.outgoing.GetCustomerPort

@Service
class CustomerService(
    val getCustomerPort: GetCustomerPort
) : GetCustomerUseCase{
    override fun getCustomer(): List<Customer> {
        return getCustomerPort.getCustomer();
    }

    override fun getCustomer(getCustomerCommand: GetCustomerUseCase.GetCustomerCommand): Customer {
        val (id) = getCustomerCommand
        return getCustomerPort.getCustomer(id);
    }
}