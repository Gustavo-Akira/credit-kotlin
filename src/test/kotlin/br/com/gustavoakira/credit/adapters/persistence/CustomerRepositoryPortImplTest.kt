package br.com.gustavoakira.credit.adapters.persistence

import br.com.gustavoakira.credit.adapters.persistence.entities.CustomerEntity
import br.com.gustavoakira.credit.application.domain.Customer
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.stubbing.Answer
import java.time.LocalDate
import kotlin.test.assertContentEquals

class CustomerRepositoryPortImplTest(
    @Mock
    private val customerSpringDataRepository: CustomerSpringDataRepository,
    @InjectMocks
    private val repository: CustomerRepositoryPortImpl
) {

    @Test
    fun findAll() {
        val result: List<Customer>  = listOf(Customer(
            name = "akira",
            annualIncome = 100000.00,
            dateOfBirth = LocalDate.of(2002, 11, 22),
            email = "akiraueki2002@gmail.com",
            password = "123"
        ))
        Mockito.`when`(customerSpringDataRepository.findAll()).then(Answer {
            listOf(CustomerEntity(
                name = "akira",
                annualIncome = 100000.00,
                dateOfBirth = LocalDate.of(2002, 11, 22),
                email = "akiraueki2002@gmail.com",
                password = "123",
                id = 1L,
                employmentDetails = "",
                creditScore = 0,
                accountBalance = 0.0
            ))
        })

        assertContentEquals(result,this.repository.findAll())
    }

    @Test
    fun findById() {
    }

    @Test
    fun save() {
    }

    @Test
    fun delete() {
    }
}