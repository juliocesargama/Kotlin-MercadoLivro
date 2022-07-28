package com.mercadolivro.service

import com.mercadolivro.model.Customer
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?): List<Customer>? {

        name?.let {
            return customerRepository.findByNameContaining(it)
        }

        return customerRepository.findAll().toList()
    }

    fun getById(id: Integer): Customer? {
        return customerRepository.findById(id).orElseThrow()
    }

    fun create(customer: Customer){
        customerRepository.save(customer)
    }

    fun update(customer: Customer) {

        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Integer) {
        val customer = getById(id)
        bookService.deleteByCustomer(customer)

        customerRepository.deleteById(id)
    }

}