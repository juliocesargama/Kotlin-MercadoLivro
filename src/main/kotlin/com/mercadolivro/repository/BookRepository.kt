package com.mercadolivro.repository

import com.mercadolivro.model.Book
import com.mercadolivro.model.BookStatus
import com.mercadolivro.model.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BookRepository: JpaRepository<Book, Int> {
    fun findByStatus(available: BookStatus, page: Pageable): Page<Book>
    fun findByCustomer(customer: Customer?): Page<Book>
}