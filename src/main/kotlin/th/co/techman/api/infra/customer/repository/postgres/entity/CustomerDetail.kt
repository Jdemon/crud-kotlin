package th.co.techman.api.infra.customer.repository.postgres.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "customers")
open class CustomerDetail: BaseModel() {
    @Column(length = 255, nullable = false)
    open var firstName: String = ""
    @Column(length = 255, nullable = false)
    open var lastName: String = ""
    @Column(length = 50, nullable = false)
    open var citizenId: String = ""
    @Column(length = 50)
    open var passportNo: String? = null
}