package br.com.gustavoakira.credit.application.domain

import java.time.LocalDate

data class Customer(
    private val id: Long = 0,
    private val name: String,
    private val dateOfBirth: LocalDate,
    private val annualIncome: Double,
    private val employmentDetails: String = "",
    private val creditScore: Int = 0,
    private val accountBalance: Double = 0.0,
    private val email: String,
    private val password: String
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