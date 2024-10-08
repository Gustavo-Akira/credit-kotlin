package br.com.gustavoakira.credit.adapters.outbound.persistence

import br.com.gustavoakira.credit.adapters.outbound.persistence.entities.CustomerEntity
import br.com.gustavoakira.credit.adapters.outbound.persistence.specification.CustomerEntitySpecification
import br.com.gustavoakira.credit.application.criteria.CustomerFilter
import br.com.gustavoakira.credit.application.domain.Customer
import br.com.gustavoakira.credit.application.ports.CustomerRepositoryPort

class CustomerRepositoryPortImpl(
    private val customerSpringDataRepository: CustomerSpringDataRepository
) : CustomerRepositoryPort {
    override fun findAll(customerFilter: CustomerFilter): List<Customer> {
        val customerEntitySpecification= CustomerEntitySpecification(customerFilter)
        return customerSpringDataRepository.findAll(customerEntitySpecification).map {
            it.toDomain()
        }
    }

    override fun findById(id: Long): Customer {
        return  customerSpringDataRepository.findById(id).orElseThrow().toDomain()
    }

    override fun save(customer: Customer): Customer {
        return customerSpringDataRepository.save(CustomerEntity.fromDomain(customer)).toDomain()
    }

    override fun delete(id: Long): Unit {
        customerSpringDataRepository.delete(customerSpringDataRepository.findById(id).orElseThrow())
    }
}