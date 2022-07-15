package com.mercadolivro.model

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity(name="customer")
data class Customer (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Integer?,

    @Column
    var name: String,

    @Column
    var email: String)