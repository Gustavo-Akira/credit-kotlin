package br.com.gustavoakira.credit.application.domain

import java.time.LocalDate

data class Customer(
     val id: Long = 0,
     val name: String,
     val dateOfBirth: LocalDate,
     val annualIncome: Double,
     val employmentDetails: String = "",
     val creditScore: Int = 0,
     val accountBalance: Double = 0.0,
     val email: String,
     val password: String
) {

    init {
        require(name.isNotEmpty()) {
            "name cannot be empty"
        }
        require(dateOfBirth.isBefore(LocalDate.now().minusYears(18))) {
            "date of birth cannot be after 18 years"
        }
        require(annualIncome > 0.0) {
            "Annual income cannot be zero or negative"
        }
        require(email.isNotEmpty()){
            "email cannot be empty"
        }
        require(password.isNotEmpty()){
            "password cannot be empty"
        }
    }


}