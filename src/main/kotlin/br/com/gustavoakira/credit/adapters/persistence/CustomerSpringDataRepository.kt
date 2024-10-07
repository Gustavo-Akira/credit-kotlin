package br.com.gustavoakira.credit.adapters.persistence

import br.com.gustavoakira.credit.adapters.persistence.entities.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CustomerSpringDataRepository : JpaRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {
}