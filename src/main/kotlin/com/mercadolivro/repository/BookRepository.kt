package com.mercadolivro.repository

import com.mercadolivro.model.Book
import com.mercadolivro.model.BookStatus
import com.mercadolivro.model.Customer
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<Book, Integer> {
    abstract fun findByStatus(available: BookStatus): List<Book>
    abstract fun findByCustomer(customer: Customer?): List<Book
            >
}