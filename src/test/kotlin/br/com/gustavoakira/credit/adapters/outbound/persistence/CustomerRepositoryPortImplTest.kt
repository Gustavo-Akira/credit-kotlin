package br.com.gustavoakira.credit.adapters.outbound.persistence

import br.com.gustavoakira.credit.adapters.outbound.persistence.entities.CustomerEntity
import br.com.gustavoakira.credit.adapters.outbound.persistence.specification.CustomerEntitySpecification
import br.com.gustavoakira.credit.application.criteria.CustomerFilter
import br.com.gustavoakira.credit.application.domain.Customer
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.function.Executable
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.stubbing.Answer
import java.time.LocalDate
import java.util.*
import javax.swing.text.html.Option
import kotlin.test.assertContentEquals
@ExtendWith(MockitoExtension::class)
class CustomerRepositoryPortImplTest{

    @Mock
    private lateinit var customerSpringDataRepository: CustomerSpringDataRepository

    @InjectMocks
    private lateinit var repository: CustomerRepositoryPortImpl

    @Test
    fun findAll() {
        val result: List<Customer>  = listOf(Customer(
            name = "akira",
            annualIncome = 100000.00,
            dateOfBirth = LocalDate.of(2002, 11, 22),
            email = "akiraueki2002@gmail.com",
            password = "123"
        ))
        val filter = CustomerFilter()
        Mockito.`when`(customerSpringDataRepository.findAll(CustomerEntitySpecification(filter))).then(Answer {
            listOf(
                CustomerEntity(
                name = "akira",
                annualIncome = 100000.00,
                dateOfBirth = LocalDate.of(2002, 11, 22),
                email = "akiraueki2002@gmail.com",
                password = "123",
                id = 0L,
                employmentDetails = "",
                creditScore = 0,
                accountBalance = 0.0
            )
            )
        })

        assertContentEquals(result,this.repository.findAll(filter))
        Mockito.verifyNoMoreInteractions(customerSpringDataRepository)
    }

    @Test
    fun findById() {
        val customer: Customer = Customer(
            name = "akira",
            annualIncome = 100000.00,
            dateOfBirth = LocalDate.of(2002, 11, 22),
            email = "akiraueki2002@gmail.com",
            password = "123"
        )
        Mockito.`when`(customerSpringDataRepository.findById(1L)).then(Answer {
          Optional.of(CustomerEntity.fromDomain(customer))
        })
        assertEquals(customer, this.repository.findById(1L));
        Mockito.verifyNoMoreInteractions(customerSpringDataRepository)
    }

    @Test
    fun save() {
        val customer: Customer = Customer(
            name = "akira",
            annualIncome = 100000.00,
            dateOfBirth = LocalDate.of(2002, 11, 22),
            email = "akiraueki2002@gmail.com",
            password = "123"
        )

        Mockito.`when`(customerSpringDataRepository.save(CustomerEntity.fromDomain(customer))).then(Answer {
            CustomerEntity.fromDomain(customer)
        });

        assertEquals(customer, this.repository.save(customer))
        Mockito.verifyNoMoreInteractions(customerSpringDataRepository)
    }

    @Test
    fun delete() {
        val customerEntity: CustomerEntity = CustomerEntity(
            name = "akira",
            annualIncome = 100000.00,
            dateOfBirth = LocalDate.of(2002, 11, 22),
            email = "akiraueki2002@gmail.com",
            password = "123",
            id = 1L,
            employmentDetails = "",
            creditScore = 0,
            accountBalance = 0.0
        )
        Mockito.`when`(this.customerSpringDataRepository.findById(1L)).thenReturn(Optional.of(customerEntity))
        doNothing().`when`(customerSpringDataRepository).delete(customerEntity);
        assertDoesNotThrow(Executable { this.repository.delete(1L) })
        Mockito.verifyNoMoreInteractions(customerSpringDataRepository)
    }
}