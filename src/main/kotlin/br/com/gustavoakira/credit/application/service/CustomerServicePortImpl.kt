package br.com.gustavoakira.credit.application.service

import br.com.gustavoakira.credit.application.criteria.CustomerFilter
import br.com.gustavoakira.credit.application.domain.Customer
import br.com.gustavoakira.credit.application.ports.CustomerRepositoryPort
import br.com.gustavoakira.credit.application.ports.CustomerServicePort
import org.springframework.stereotype.Service

@Service
class CustomerServicePortImpl(
    private val customerRepositoryPort: CustomerRepositoryPort
) : CustomerServicePort {
    override fun findAll(
        name: String?,
        minAnnualIncome: Double?,
        maxAnnualIncome: Double?,
        minCreditScore: Int?,
        email: String?
    ): List<Customer> {
        val filter = CustomerFilter(
            name = name,
            minAnnualIncome = minAnnualIncome,
            maxAnnualIncome = maxAnnualIncome,
            minCreditScore = minCreditScore,
            email = email
        )

        return customerRepositoryPort.findAll(filter)
    }

    override fun findById(id: Long): Customer {
        return customerRepositoryPort.findById(id)
    }

    override fun save(customer: Customer): Customer {
        return customerRepositoryPort.save(customer)
    }

    override fun delete(id: Long) {
        return customerRepositoryPort.delete(id)
    }
}