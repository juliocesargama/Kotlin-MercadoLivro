package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.extension.toCustomer
import com.mercadolivro.model.Customer
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<Customer>? {
       return customerService.getAll(name)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Integer): Customer? {
        return customerService.getById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest){
        customerService.create(customer.toCustomer())
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Integer, @RequestBody customer: PutCustomerRequest) {
        customerService.update(customer.toCustomer(id))
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Integer) {
        customerService.delete(id)
    }
}