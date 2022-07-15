package com.mercadolivro.repository

import com.mercadolivro.model.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository:CrudRepository<Customer, Integer> {

    fun findByNameContaining(name: String): List<Customer>
}