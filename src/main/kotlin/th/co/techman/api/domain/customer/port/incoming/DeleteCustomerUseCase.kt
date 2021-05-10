package th.co.techman.api.domain.customer.port.incoming

import org.valiktor.functions.isNotNull
import org.valiktor.functions.isNotZero
import org.valiktor.validate

interface DeleteCustomerUseCase {
    fun deleteCustomer(deleteCustomerCommand: DeleteCustomerCommand)

    data class DeleteCustomerCommand(
        val id: Long
    ) {
        init {
            validate(this) {
                validate(DeleteCustomerCommand::id)
                    .isNotNull()
                    .isNotZero()
            }
        }
    }
}
