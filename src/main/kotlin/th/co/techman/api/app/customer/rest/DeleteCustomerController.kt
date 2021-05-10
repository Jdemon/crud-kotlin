package th.co.techman.api.app.customer.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import th.co.techman.api.app.customer.model.CustomerResponse
import th.co.techman.api.app.customer.model.customerResponse
import th.co.techman.api.domain.customer.port.incoming.DeleteCustomerUseCase
import th.co.techman.api.domain.customer.port.incoming.GetCustomerUseCase

@RestController
@RequestMapping(value = ["customers"])
class DeleteCustomerController {

    @Autowired
    lateinit var deleteCustomerUseCase: DeleteCustomerUseCase

    @DeleteMapping(
        value = ["{id}"]
    )
    fun delete(@PathVariable(value = "id") id: Long) {
        deleteCustomerUseCase.deleteCustomer(id.run {
            DeleteCustomerUseCase.DeleteCustomerCommand(id)
        })
    }
}