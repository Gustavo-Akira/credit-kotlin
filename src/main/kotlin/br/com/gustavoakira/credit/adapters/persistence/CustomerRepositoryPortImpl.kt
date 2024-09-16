package br.com.gustavoakira.credit.adapters.persistence

import br.com.gustavoakira.credit.application.domain.Customer
import br.com.gustavoakira.credit.application.ports.CustomerRepositoryPort

class CustomerRepositoryPortImpl(
    private val customerSpringDataRepository: CustomerSpringDataRepository
) : CustomerRepositoryPort {
    override fun findAll(): List<Customer> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): Customer {
        TODO("Not yet implemented")
    }

    override fun save(customer: Customer): Customer {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}