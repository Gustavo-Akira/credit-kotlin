package br.com.gustavoakira.credit.application.ports

import br.com.gustavoakira.credit.application.domain.Customer

interface CustomerRepositoryPort {
    fun findAll():List<Customer>
    fun findById(id: Long):Customer
    fun save(customer: Customer) : Customer
    fun delete(id: Long)
}