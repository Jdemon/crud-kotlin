package th.co.techman.api.domain.customer.service

import org.springframework.stereotype.Service
import th.co.techman.api.domain.common.exception.DataNotFoundException
import th.co.techman.api.domain.customer.model.Customer
import th.co.techman.api.domain.customer.port.incoming.DeleteCustomerUseCase
import th.co.techman.api.domain.customer.port.incoming.GetCustomerUseCase
import th.co.techman.api.domain.customer.port.incoming.SaveCustomerUseCase
import th.co.techman.api.domain.customer.port.incoming.UpdateCustomerUseCase
import th.co.techman.api.domain.customer.port.outgoing.DeleteCustomerPort
import th.co.techman.api.domain.customer.port.outgoing.GetCustomerPort
import th.co.techman.api.domain.customer.port.outgoing.SaveCustomerPort
import th.co.techman.api.domain.customer.port.outgoing.UpdateCustomerPort

@Service
class CustomerService(
    val getCustomerPort: GetCustomerPort,
    val saveCustomerPort: SaveCustomerPort,
    val updateCustomerPort: UpdateCustomerPort,
    val deleteCustomerPort: DeleteCustomerPort
) : GetCustomerUseCase, SaveCustomerUseCase, UpdateCustomerUseCase, DeleteCustomerUseCase {
    override fun getCustomer(): List<Customer> = getCustomerPort.getCustomer()

    override fun getCustomer(getCustomerCommand: GetCustomerUseCase.GetCustomerCommand) =
        getCustomerPort.getCustomer(getCustomerCommand.id)

    override fun saveCustomer(saveCustomerCommand: SaveCustomerUseCase.SaveCustomerCommand) =
        saveCustomerPort.saveCustomer(
            Customer().also {
                it.citizenId = saveCustomerCommand.citizenId
                it.firstName = saveCustomerCommand.firstName
                it.lastName = saveCustomerCommand.lastName
                it.passportNo = saveCustomerCommand.passportNo
            }
        )

    override fun updateCustomer(updateCustomerCommand: UpdateCustomerUseCase.UpdateCustomerCommand): Customer {
        val (id, firstName, lastName, citizenId, passportNo) = updateCustomerCommand
        if (!getCustomerPort.exist(id)) {
            throw DataNotFoundException("No found customer.")
        }
        return updateCustomerPort.updateCustomer(Customer(id, firstName, lastName, passportNo, citizenId))
    }

    override fun deleteCustomer(deleteCustomerCommand: DeleteCustomerUseCase.DeleteCustomerCommand) {
        deleteCustomerPort.deleteCustomer(deleteCustomerCommand.id)
    }
}
