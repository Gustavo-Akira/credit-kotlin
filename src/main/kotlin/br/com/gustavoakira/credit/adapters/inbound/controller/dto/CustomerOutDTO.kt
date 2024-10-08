package br.com.gustavoakira.credit.adapters.inbound.controller.dto

import br.com.gustavoakira.credit.application.domain.Customer
import java.time.LocalDate

data class CustomerOutDTO(val id: Long = 0,
                          val name: String,
                          val dateOfBirth: LocalDate,
                          val annualIncome: Double,
                          val employmentDetails: String = "",
                          val creditScore: Int = 0,
                          val accountBalance: Double = 0.0,
                          val email: String){
    companion object{
        fun fromDomain(customer: Customer): CustomerOutDTO{
            return CustomerOutDTO(
                id=customer.id,
                name = customer.name,
                dateOfBirth = customer.dateOfBirth,
                annualIncome = customer.annualIncome,
                employmentDetails = customer.employmentDetails,
                creditScore = customer.creditScore,
                accountBalance = customer.accountBalance,
                email = customer.email,

            )
        }
    }
}
