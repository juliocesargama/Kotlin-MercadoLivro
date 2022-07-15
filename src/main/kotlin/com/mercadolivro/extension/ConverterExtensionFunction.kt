package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.Customer

fun PostCustomerRequest.toCustomer() : Customer{
    return Customer(id = null, name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomer(id: Integer) : Customer{
    return Customer(id = id, name = this.name, email = this.email)
}