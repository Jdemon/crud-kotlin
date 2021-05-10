package th.co.techman.api.app.customer.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import th.co.techman.api.domain.customer.port.incoming.DeleteCustomerUseCase

@RestController
@RequestMapping(value = ["customers"])
class DeleteCustomerController {

    @Autowired
    lateinit var deleteCustomerUseCase: DeleteCustomerUseCase

    @DeleteMapping(
        value = ["{id}"]
    )
    fun delete(
        @PathVariable(
            value = "id"
        ) id: Long
    ) {
        deleteCustomerUseCase.deleteCustomer(
            id.run {
                DeleteCustomerUseCase.DeleteCustomerCommand(id)
            }
        )
    }
}
