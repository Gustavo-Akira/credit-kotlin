package br.com.gustavoakira.credit.adapters.inbound.controller

import br.com.gustavoakira.credit.adapters.inbound.controller.dto.CustomerDTO
import br.com.gustavoakira.credit.adapters.inbound.controller.dto.CustomerOutDTO
import br.com.gustavoakira.credit.adapters.inbound.controller.dto.UpdateCustomerDTO
import br.com.gustavoakira.credit.application.ports.CustomerServicePort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/v1/customer")
class RestCustomerController(
    private val customerServicePort: CustomerServicePort
){
    @GetMapping()
    fun getCustomers(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) minAnnualIncome: Double?,
        @RequestParam(required = false) maxAnnualIncome: Double?,
        @RequestParam(required = false) minCreditScore: Int?,
        @RequestParam(required = false) email: String?
    ): List<CustomerOutDTO> {
        // Passing request params to the service to get filtered customers
        return customerServicePort.findAll(
            name = name,
            minAnnualIncome = minAnnualIncome,
            maxAnnualIncome = maxAnnualIncome,
            minCreditScore = minCreditScore,
            email = email
        ).map { CustomerOutDTO.fromDomain(it) }
    }
    @GetMapping("/{id}")
    fun getCustomer(@PathVariable("id") id: Long): CustomerOutDTO{
        return customerServicePort.findById(id).let {
            CustomerOutDTO.fromDomain(it)
        }
    }

    @PostMapping()
    fun saveCustomer(@RequestBody() customerDTO: CustomerDTO): ResponseEntity<Long>{
        val id: Long = customerServicePort.save(customerDTO.toDomain()).id
        return ResponseEntity.created(URI("/api/v1/customers/"+id.toString())).body(id)
    }

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable("id") id: Long, @RequestBody() updateCustomerDTO: UpdateCustomerDTO): CustomerOutDTO{
        return customerServicePort.save(updateCustomerDTO.toDomain()).let {
            CustomerOutDTO.fromDomain(it)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable("id") id: Long){
        return customerServicePort.delete(id)
    }
}
