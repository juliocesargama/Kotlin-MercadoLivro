package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toBook
import com.mercadolivro.extension.toResponse
import com.mercadolivro.model.Book
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.Collections.emptyList

@RestController
@RequestMapping("book")
class BookController(
        val bookService: BookService,
        val customerService: CustomerService
) {

    @GetMapping
    fun getAll(): List<BookResponse>{
       return bookService.getAll().map { it.toResponse() }
    }

    @GetMapping("/available")
    fun getActives(): List<BookResponse>{
        return bookService.findAvailable().map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookResponse{
        return bookService.findById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun create(@RequestBody request: PostBookRequest){
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBook(customer))
    }

    @PutMapping
    @ResponseStatus(NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest){
        val bookSaved = bookService.findById(id)
        bookService.update(book.toBook(bookSaved))
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    fun delete(@PathVariable id: Int){
        bookService.delete(id)
    }
}