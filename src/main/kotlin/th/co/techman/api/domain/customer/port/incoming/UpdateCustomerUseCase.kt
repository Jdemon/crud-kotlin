package th.co.techman.api.domain.customer.port.incoming

import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotNull
import org.valiktor.functions.matches
import org.valiktor.validate
import th.co.techman.api.domain.common.functions.isThaiCitizenId
import th.co.techman.api.domain.customer.model.Customer

interface UpdateCustomerUseCase {
    fun updateCustomer(updateCustomerCommand: UpdateCustomerCommand): Customer

    data class UpdateCustomerCommand(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val citizenId: String?,
        val passportNo: String?
    ) {
        init {
            validate(this) {
                validate(UpdateCustomerCommand::firstName)
                    .isNotNull()
                    .isNotBlank()
                validate(UpdateCustomerCommand::lastName)
                    .isNotNull()
                    .isNotBlank()
                validate(UpdateCustomerCommand::passportNo)
                    .isNotBlank()
                validate(UpdateCustomerCommand::citizenId)
                    .isNotNull()
                    .isNotBlank()
                    .isThaiCitizenId()
            }
        }
    }
}