package br.com.gustavoakira.credit.adapters.inbound.controller

import br.com.gustavoakira.credit.adapters.config.SecurityFilterConfig
import br.com.gustavoakira.credit.adapters.inbound.controller.dto.CustomerDTO
import br.com.gustavoakira.credit.adapters.inbound.controller.dto.UpdateCustomerDTO
import br.com.gustavoakira.credit.application.domain.Customer
import br.com.gustavoakira.credit.application.ports.CustomerServicePort
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate

@WebMvcTest(RestCustomerController::class)
@Import(SecurityFilterConfig::class)
class RestCustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var customerServicePort: CustomerServicePort

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @WithMockUser(username = "testuser", roles = ["USER"])
    fun `test get all customers`() {
        val customers = listOf(
            Customer(1L, "John Doe", LocalDate.of(1990, 1, 1), 50000.0, "Engineer", 750, 20000.0, "john@example.com", password = "dsdsaf"),
            Customer(2L, "Jane Smith", LocalDate.of(1985, 2, 10), 60000.0, "Teacher", 800, 30000.0, "jane@example.com", password = "dsfsad")
        )

        `when`(customerServicePort.findAll(any(), any(), any(), any(), any())).thenReturn(customers)

        mockMvc.perform(get("/api/v1/customer"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].name").value("John Doe"))
            .andExpect(jsonPath("$[1].name").value("Jane Smith"))
    }

    @Test
    @WithMockUser(username = "testuser", roles = ["USER"])
    fun `test get customer by id`() {
        val customer = Customer(1L, "John Doe", LocalDate.of(1990, 1, 1), 50000.0, "Engineer", 750, 20000.0, "john@example.com","asdffdsa")

        `when`(customerServicePort.findById(1L)).thenReturn(customer)

        mockMvc.perform(get("/api/v1/customer/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("John Doe"))
    }

    @Test
    @WithMockUser(username = "testuser", roles = ["USER"])
    fun `test save customer`() {
        val customerDTO = CustomerDTO(
            name = "John Doe",
            dateOfBirth = LocalDate.of(1990, 1, 1),
            annualIncome = 50000.0,
            employmentDetails = "Engineer",
            accountBalance = 20000.0,
            email = "john@example.com",
            password = "fdsfas"
        )

        val savedCustomer = customerDTO.toDomain()


        `when`(customerServicePort.save(savedCustomer)).thenReturn(savedCustomer)

        mockMvc.perform(post("/api/v1/customer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(customerDTO)))
            .andExpect(status().isCreated)
            .andExpect(header().string("Location", "/api/v1/customers/0"))
            .andExpect(content().string("0"))
    }

    @Test
    @WithMockUser(username = "testuser", roles = ["USER"])
    fun `test update customer`() {
        val updateCustomerDTO = UpdateCustomerDTO(
            name = "John Doe Updated",
            annualIncome = 55000.0,
            employmentDetails = "Senior Engineer",
            creditScore = 760,
            accountBalance = 25000.0,
            email = "johnupdated@example.com",
            password = "afsa",
            id = 1L,
            dateOfBirth = LocalDate.of(1900,1,19)
        )

        val updatedCustomer = updateCustomerDTO.toDomain().copy(id = 1L)


        `when`(customerServicePort.save(updatedCustomer)).thenReturn(updatedCustomer)

        mockMvc.perform(put("/api/v1/customer/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateCustomerDTO)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("John Doe Updated"))
            .andExpect(jsonPath("$.annualIncome").value(55000.0))
    }

    @Test
    @WithMockUser(username = "testuser", roles = ["USER"])
    fun `test delete customer`() {
        doNothing().`when`(customerServicePort).delete(1L)

        mockMvc.perform(delete("/api/v1/customer/1"))
            .andExpect(status().isOk)

        verify(customerServicePort, times(1)).delete(1L)
    }
}