package com.mercadolivro.model

import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Schema(description = "Model for a Customer")
@Entity(name="customer")
data class Customer (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Schema(
        description = "A customer identification",
        example = "1",
        type = "Integer"
    )
    var id: Integer?,

    @Column
    @field:Schema(
        description = "A customer full name",
        example = "John Doe",
        type = "String"
    )
    var name: String,

    @Column
    @field:Schema(
        description = "A customer email",
        example = "john@doe.com",
        type = "String",
    )
    var email: String)