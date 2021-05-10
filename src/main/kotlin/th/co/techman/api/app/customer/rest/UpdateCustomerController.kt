package th.co.techman.api.app.customer.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import th.co.techman.api.app.customer.model.CustomerRequest
import th.co.techman.api.app.customer.model.CustomerResponse
import th.co.techman.api.app.customer.model.customerResponse
import th.co.techman.api.domain.customer.port.incoming.UpdateCustomerUseCase

@RestController
@RequestMapping(value = ["customers"])
class UpdateCustomerController {

    @Autowired
    lateinit var updateCustomerUseCase: UpdateCustomerUseCase

    @PutMapping("{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody customerRequest: CustomerRequest): CustomerResponse {
        return updateCustomerUseCase.updateCustomer(
            UpdateCustomerUseCase.UpdateCustomerCommand(
                id = id,
                firstName = customerRequest.firstName,
                lastName = customerRequest.lastName,
                citizenId = customerRequest.citizenId ?: customerRequest.nationalityId,
                passportNo = customerRequest.passportNo
            )
        ).customerResponse
    }
}
