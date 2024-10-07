package br.com.gustavoakira.credit.application.ports

import br.com.gustavoakira.credit.application.domain.Customer

interface CustomerServicePort {
    fun findAll(name: String?,
                minAnnualIncome: Double?,
                maxAnnualIncome: Double?,
                minCreditScore: Int?,
                email: String?):List<Customer>
    fun findById(id: Long): Customer
    fun save(customer: Customer) : Customer
    fun delete(id: Long): Unit
}