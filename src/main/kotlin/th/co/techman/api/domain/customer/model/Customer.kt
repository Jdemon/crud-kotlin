package th.co.techman.api.domain.customer.model

import java.io.Serializable

open class Customer: Serializable {
    open var id: Long? = null
    open var firstName: String? = null
    open var lastName: String? = null
    open var passportNo: String? = null
    open var citizenId: String? = null
}
