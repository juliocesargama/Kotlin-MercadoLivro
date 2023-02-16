package com.mercadolivro.service

import com.mercadolivro.model.Customer
import com.mercadolivro.model.CustomerStatus.INACTIVE
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

    fun findById(id: Int): Customer? {
        return customerRepository.findById(id).orElseThrow()
    }

    fun create(customer: Customer){
        customerRepository.save(customer)
    }

    fun update(customer: Customer?) {

        if(!customerRepository.existsById(customer?.id!!)){
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)

        if(customer != null) {
            bookService.deleteByCustomer(customer)
            customer?.status = INACTIVE
            customerRepository.save(customer)
        }else{
            throw Exception()
        }
    }

}