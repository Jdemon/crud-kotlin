package th.co.techman.api.infra.customer.repository.postgres.repository

import org.springframework.data.jpa.repository.JpaRepository
import th.co.techman.api.infra.customer.repository.postgres.entity.CustomerDetail

interface CustomerRepository : JpaRepository<CustomerDetail, Long>
