package th.co.techman.api.app.customer.model

data class CustomerRequest(
    val firstName: String,
    val lastName: String,
    val nationalityId: String?,
    val citizenId: String?,
    val passportNo: String?
)
