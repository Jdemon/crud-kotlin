package th.co.techman.api.app.customer.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import th.co.techman.api.app.customer.model.CustomerRequest
import th.co.techman.api.app.customer.model.CustomerResponse
import th.co.techman.api.app.customer.model.customerResponse
import th.co.techman.api.domain.customer.port.incoming.SaveCustomerUseCase

@RestController
@RequestMapping(value = ["customers"])
class SaveCustomerController {

    @Autowired
    lateinit var saveCustomerUseCase: SaveCustomerUseCase

    @PostMapping
    fun saveCustomer(@RequestBody customerRequest: CustomerRequest): CustomerResponse {
        return saveCustomerUseCase.saveCustomer(
            customerRequest.run {
                SaveCustomerUseCase.SaveCustomerCommand(
                    customerRequest.firstName,
                    customerRequest.lastName,
                    citizenId = customerRequest.citizenId ?: customerRequest.nationalityId,
                    customerRequest.passportNo
                )
            }
        ).customerResponse
    }
}