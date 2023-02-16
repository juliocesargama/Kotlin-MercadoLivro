package com.mercadolivro.service

import com.mercadolivro.model.Book
import com.mercadolivro.model.BookStatus
import com.mercadolivro.model.BookStatus.DELETED
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
        val bookRepository: BookRepository
) {
    fun create(book: Book) {
        bookRepository.save(book)
    }

    fun getAll(pageable: Pageable): Page<Book> {
        return bookRepository.findAll(pageable)
    }

    fun findAvailable(pageable: Pageable): Page<Book> {
        return bookRepository.findByStatus(BookStatus.AVAILABLE, pageable)
    }

    fun findById(id: Int): Book {
        return bookRepository.findById(id).orElseThrow()
    }

    fun update(book: Book) {
        bookRepository.save(book)
    }

    fun delete(id: Int){
        val book = findById(id)

        book.status = DELETED
        update(book)
    }

    fun deleteByCustomer(customer: Customer?) {
       val books =  bookRepository.findByCustomer(customer)

        for(book in books){
            book.status = DELETED
        }

        bookRepository.saveAll(books)
    }
}
