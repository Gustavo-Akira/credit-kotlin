package br.com.gustavoakira.credit.application.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate

class CustomerTest{
    @Test
    fun createCustomer(){
        assertDoesNotThrow {
            Customer(
                name = "akira",
                annualIncome = 100000.00,
                dateOfBirth = LocalDate.of(2002, 11, 22),
                email = "akiraueki2002@gmail.com",
                password = "123"
            )
        }
    }

    @Test
    fun shouldThrowIllegalArgumentExceptionWhenCustomerNameIsInvalid(){
       assertThrows<IllegalArgumentException> {
           Customer(
               name = "",
               annualIncome = 100000.00,
               dateOfBirth = LocalDate.of(2002, 11, 22),
               email = "akiraueki2002@gmail.com",
               password = "123"
           )
       }
    }
    @Test
    fun shouldThrowIllegalArgumentExceptionWhenCustomerIncomeIsNegative(){
        assertThrows<IllegalArgumentException> {
            Customer(
                name = "afsa",
                annualIncome = -100000.00,
                dateOfBirth = LocalDate.of(2002, 11, 22),
                email = "akiraueki2002@gmail.com",
                password = "123"
            )
        }
    }

    @Test
    fun shouldThrowIllegalArgumentExceptionWhenCustomerIncomeIsZero(){
        assertThrows<IllegalArgumentException> {
            Customer(
                name = "afsa",
                annualIncome = 0.0,
                dateOfBirth = LocalDate.of(2002, 11, 22),
                email = "akiraueki2002@gmail.com",
                password = "123"
            )
        }
    }

    @Test
    fun shouldThrowIllegalArgumentExceptionWhenCustomerBirthDateIsInvalid(){
        assertThrows<IllegalArgumentException> {
            Customer(
                name = "afsa",
                annualIncome = 0.0,
                dateOfBirth = LocalDate.of(2020, 11, 22),
                email = "akiraueki2002@gmail.com",
                password = "123"
            )
        }
    }

    @Test
    fun shouldThrowIllegalArgumentExceptionWhenCustomersEmailIsInvalid(){
        assertThrows<IllegalArgumentException> {
            Customer(
                name = "afsa",
                annualIncome = 0.0,
                dateOfBirth = LocalDate.of(2020, 11, 22),
                email = "",
                password = "123"
            )
        }
    }

    @Test
    fun shouldThrowIllegalArgumentExceptionWhenCustomerPasswordIsInvalid(){
        assertThrows<IllegalArgumentException> {
            Customer(
                name = "afsa",
                annualIncome = 0.0,
                dateOfBirth = LocalDate.of(2020, 11, 22),
                email = "akiraueki2002@gmail.com",
                password = ""
            )
        }
    }
}