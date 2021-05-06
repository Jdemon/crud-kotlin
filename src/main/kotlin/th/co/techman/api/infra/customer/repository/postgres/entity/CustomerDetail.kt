package th.co.techman.api.infra.customer.repository.postgres.entity

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "customers")
class CustomerDetail(
    id: Long,
    open val firstName: String?,
    open val lastName: String?,
    open val passportNo: String?,
    open val citizenId: String?,
): BaseModel(id)
