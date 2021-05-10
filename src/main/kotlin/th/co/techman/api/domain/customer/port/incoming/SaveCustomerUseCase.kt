package th.co.techman.api.domain.customer.port.incoming

import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotNull
import org.valiktor.validate
import th.co.techman.api.domain.common.functions.isThaiCitizenId
import th.co.techman.api.domain.customer.model.Customer

interface SaveCustomerUseCase {
    fun saveCustomer(saveCustomerCommand: SaveCustomerCommand): Customer

    data class SaveCustomerCommand(
        val firstName: String,
        val lastName: String,
        val citizenId: String?,
        val passportNo: String?
    ) {
        init {
            validate(this) {
                validate(SaveCustomerCommand::firstName)
                    .isNotNull()
                    .isNotBlank()
                validate(SaveCustomerCommand::lastName)
                    .isNotNull()
                    .isNotBlank()
                validate(SaveCustomerCommand::passportNo)
                    .isNotBlank()
                validate(SaveCustomerCommand::citizenId)
                    .isNotNull()
                    .isNotBlank()
                    .isThaiCitizenId()
            }
        }
    }
}
