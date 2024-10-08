package br.com.gustavoakira.credit.adapters.outbound.persistence.entities

import br.com.gustavoakira.credit.application.domain.Customer
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long,
    @Column()
    private val name: String,
    @Column(name = "birth_date")
    private val dateOfBirth: LocalDate,
    @Column(name = "annual_income")
    private val annualIncome: Double,
    @Column(name = "employment_details")
    private val employmentDetails: String,
    @Column(name = "credit_score")
    private val creditScore: Int,
    @Column(name = "account_balance")
    private val accountBalance: Double,
    @Column()
    private val email: String,
    @Column()
    private val password: String
) {
    companion object {
        fun fromDomain(customer: Customer): CustomerEntity {
            return CustomerEntity(
                name = customer.name,
                annualIncome = customer.annualIncome,
                dateOfBirth = customer.dateOfBirth,
                email = customer.email,
                password = customer.password,
                id = customer.id,
                employmentDetails = customer.employmentDetails,
                creditScore = customer.creditScore,
                accountBalance = customer.accountBalance
            )
        }
    }

    fun toDomain(): Customer{
        return Customer(
            name = name,
            annualIncome = annualIncome,
            dateOfBirth = dateOfBirth,
            email = email,
            password = password,
            id = id,
            employmentDetails = employmentDetails,
            creditScore = creditScore,
            accountBalance = accountBalance
        )
    }
}