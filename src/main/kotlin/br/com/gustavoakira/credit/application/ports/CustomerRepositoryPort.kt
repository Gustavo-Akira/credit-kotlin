package br.com.gustavoakira.credit.application.ports

import br.com.gustavoakira.credit.application.criteria.CustomerFilter
import br.com.gustavoakira.credit.application.domain.Customer
import java.time.LocalDate

interface CustomerRepositoryPort {
    fun findAll(customerFilter: CustomerFilter):List<Customer>
    fun findById(id: Long):Customer
    fun save(customer: Customer) : Customer
    fun delete(id: Long): Unit
}