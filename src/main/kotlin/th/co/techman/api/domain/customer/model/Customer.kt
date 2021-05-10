package th.co.techman.api.domain.customer.model

import java.io.Serializable

open class Customer : Serializable {

    constructor()

    constructor(id: Long?, firstName: String?, lastName: String?, passportNo: String?, citizenId: String?) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.passportNo = passportNo
        this.citizenId = citizenId
    }

    open var id: Long? = null
    open var firstName: String? = null
    open var lastName: String? = null
    open var passportNo: String? = null
    open var citizenId: String? = null
}
