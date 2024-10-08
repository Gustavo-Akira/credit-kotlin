package br.com.gustavoakira.credit.adapters.inbound.controller.dto

import br.com.gustavoakira.credit.application.domain.Customer
import java.time.LocalDate

data class CustomerDTO(
    val name: String,
    val dateOfBirth: LocalDate,
    val annualIncome: Double,
    val employmentDetails: String = "",
    val accountBalance: Double = 0.0,
    val email: String,
    val password: String
) {
    fun toDomain(): Customer {
        return Customer(
            name = this.name,
            dateOfBirth = this.dateOfBirth,
            annualIncome = this.annualIncome,
            employmentDetails = this.employmentDetails,
            accountBalance = this.accountBalance,
            email = this.email,
            password = this.password
        )
    }
}
