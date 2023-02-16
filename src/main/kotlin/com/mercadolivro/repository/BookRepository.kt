package com.mercadolivro.repository

import com.mercadolivro.model.Book
import com.mercadolivro.model.BookStatus
import com.mercadolivro.model.Customer
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<Book, Int> {
    fun findByStatus(available: BookStatus): List<Book>
    fun findByCustomer(customer: Customer?): List<Book>
}