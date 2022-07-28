package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.Book
import com.mercadolivro.model.BookStatus.AVAILABLE
import com.mercadolivro.model.Customer

fun PostCustomerRequest.toCustomer() : Customer{
    return Customer(id = null, name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomer(id: Integer) : Customer{
    return Customer(id = id, name = this.name, email = this.email)
}

fun PostBookRequest.toBook(customer: Customer?) : Book{
    return Book(id = null, name = this.name, price = this.price, status = AVAILABLE, customer)
}

fun PutBookRequest.toBook(book: Book) : Book{
    return Book(id = book.id, name = this.name ?: book.name, price = this.price ?: book.price, status = book.status, customer = book.customer)
}