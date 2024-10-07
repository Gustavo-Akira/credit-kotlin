package br.com.gustavoakira.credit.application.service

import br.com.gustavoakira.credit.application.criteria.CustomerFilter
import br.com.gustavoakira.credit.application.domain.Customer
import br.com.gustavoakira.credit.application.ports.CustomerRepositoryPort
import br.com.gustavoakira.credit.application.ports.CustomerServicePort
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class CustomerServicePortImplTest {

    @Mock
    lateinit var customerRepository: CustomerRepositoryPort

    @InjectMocks
    lateinit var customerService: CustomerServicePortImpl

    @Test
    fun shouldCallRepositoryWithCorrectFilter() {
        val name = "John"
        val minAnnualIncome = 50000.0
        val maxAnnualIncome = 100000.0
        val minCreditScore = 600
        val email = "john@example.com"

        val expectedFilter = CustomerFilter(
            name = name,
            minAnnualIncome = minAnnualIncome,
            maxAnnualIncome = maxAnnualIncome,
            minCreditScore = minCreditScore,
            email = email
        )

        `when`(customerRepository.findAll(expectedFilter)).thenReturn(emptyList())

        val result = customerService.findAll(
            name = name,
            minAnnualIncome = minAnnualIncome,
            maxAnnualIncome = maxAnnualIncome,
            minCreditScore = minCreditScore,
            email = email
        )

        assertTrue(result.isEmpty())
    }

    @Test
    fun shouldReturnCustomersWhenRepositoryReturnData() {
        val name = "John Doe"
        val minAnnualIncome = 50000.0
        val maxAnnualIncome = 100000.0
        val minCreditScore = 600
        val email = "john@example.com"

        val filter = CustomerFilter(
            name = name,
            minAnnualIncome = minAnnualIncome,
            maxAnnualIncome = maxAnnualIncome,
            minCreditScore = minCreditScore,
            email = email
        )

        val customer = Customer(
            id = 1,
            name = "John",
            dateOfBirth = LocalDate.of(1985, 1, 1),
            annualIncome = 75000.0,
            employmentDetails = "Engineer",
            creditScore = 700,
            accountBalance = 5000.0,
            email = "john@example.com",
            password = "password123"
        )

        `when`(customerRepository.findAll(filter)).thenReturn(listOf(customer))

        // When: The service is called with the filter
        val result = customerService.findAll(
            name = name,
            minAnnualIncome = minAnnualIncome,
            maxAnnualIncome = maxAnnualIncome,
            minCreditScore = minCreditScore,
            email = email
        )

        assertEquals(1, result.size)
        assertEquals(customer, result[0])
    }
}