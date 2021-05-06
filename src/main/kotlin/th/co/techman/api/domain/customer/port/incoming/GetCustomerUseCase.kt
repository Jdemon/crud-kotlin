package th.co.techman.api.domain.customer.port.incoming

import org.valiktor.functions.isNotNull
import org.valiktor.validate
import th.co.techman.api.domain.customer.model.Customer

interface GetCustomerUseCase {
    fun getCustomer(): List<Customer>
    fun getCustomer(getCustomerCommand: GetCustomerCommand): Customer

    data class GetCustomerCommand(
        val id: Long
    ) {
        init {
            validate(this) {
                validate(GetCustomerCommand::id).isNotNull()
            }
        }

    }
}