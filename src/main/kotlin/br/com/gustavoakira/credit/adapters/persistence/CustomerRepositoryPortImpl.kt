package br.com.gustavoakira.credit.adapters.persistence

import br.com.gustavoakira.credit.adapters.persistence.entities.CustomerEntity
import br.com.gustavoakira.credit.application.domain.Customer
import br.com.gustavoakira.credit.application.ports.CustomerRepositoryPort

class CustomerRepositoryPortImpl(
    private val customerSpringDataRepository: CustomerSpringDataRepository
) : CustomerRepositoryPort {
    override fun findAll(): List<Customer> {
        return customerSpringDataRepository.findAll().map {
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