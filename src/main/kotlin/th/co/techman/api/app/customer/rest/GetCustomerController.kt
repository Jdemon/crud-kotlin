package th.co.techman.api.app.customer.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import th.co.techman.api.app.customer.model.CustomerResponse
import th.co.techman.api.app.customer.model.customerResponse
import th.co.techman.api.domain.customer.port.incoming.GetCustomerUseCase

@RestController
@RequestMapping(value = ["customers"])
class GetCustomerController {

    @Autowired
    lateinit var getCustomerUseCase: GetCustomerUseCase

    @GetMapping
    fun getAllCustomer() = getCustomerUseCase.getCustomer().map { CustomerResponse.fromCustomer(it) }

    @GetMapping(
        value = ["{id}"]
    )
    fun getCustomer(@PathVariable(value = "id") id: Long) = getCustomerUseCase.getCustomer(id.run {
            GetCustomerUseCase.GetCustomerCommand(id)
        }).customerResponse
}