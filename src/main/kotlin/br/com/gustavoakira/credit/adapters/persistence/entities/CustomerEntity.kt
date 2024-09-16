package br.com.gustavoakira.credit.adapters.persistence.entities

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
}