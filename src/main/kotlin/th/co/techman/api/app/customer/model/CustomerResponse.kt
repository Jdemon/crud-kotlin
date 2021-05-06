package th.co.techman.api.app.customer.model

import th.co.techman.api.domain.customer.model.Customer

data class CustomerResponse(
    val id: Long?,
    val firstName: String?,
    val lastName: String?,
    val nationalityId: String?,
    val citizenId: String?,
    val passportNo: String?
) {
    companion object {
        fun fromCustomer(customer: Customer): CustomerResponse {
            return customer.customerData
        }
    }
}

internal val Customer.customerData: CustomerResponse
    get() = CustomerResponse(
        id,
        firstName,
        lastName,
        citizenId,
        citizenId,
        passportNo
    )
