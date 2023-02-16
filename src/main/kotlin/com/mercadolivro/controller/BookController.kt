package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toBook
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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

@RestController
@RequestMapping("book")
class BookController(
        val bookService: BookService,
        val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10)pageable: Pageable): Page<BookResponse>{
       return bookService.getAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/available")
    fun getActives(@PageableDefault(page = 0, size = 5) pageable: Pageable): Page<BookResponse>{
        return bookService.findAvailable(pageable).map { it.toResponse() }
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