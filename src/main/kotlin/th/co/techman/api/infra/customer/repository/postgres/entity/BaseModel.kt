package th.co.techman.api.infra.customer.repository.postgres.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.*


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    @CreatedDate
    @Column(updatable = false, nullable = false)
    open var createdAt: Instant = Instant.now()

    @CreatedBy
    @Column(updatable = false, nullable = false)
    open var createdBy: String = ""

    @LastModifiedDate
    open var updatedAt: Instant? = null

    @LastModifiedBy
    @Column(nullable = false)
    open var updatedBy: String? = null
}
