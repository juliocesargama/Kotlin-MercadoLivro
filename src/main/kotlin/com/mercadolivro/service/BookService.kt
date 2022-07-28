package com.mercadolivro.service

import com.mercadolivro.model.Book
import com.mercadolivro.model.BookStatus
import com.mercadolivro.model.BookStatus.DELETED
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
        val bookRepository: BookRepository
) {
    fun create(book: Book) {
        bookRepository.save(book)
    }

    fun getAll(): List<Book>{
        return bookRepository.findAll().toList()
    }

    fun findAvailable(): List<Book> {
        return bookRepository.findByStatus(BookStatus.AVAILABLE)
    }

    fun findById(id: Integer): Book {
        return bookRepository.findById(id).orElseThrow()
    }

    fun update(book: Book) {
        bookRepository.save(book)
    }

    fun delete(id: Integer){
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
