package com.mercadolivro.model

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import javax.persistence.*

@Schema(description = "Model for costumer's book")
@Entity(name = "book")
data class Book (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    @field:Schema(
        description = "A book title",
        example = "Lord of the Rings: The Fellowship of the Ring",
        type = "String",
    )
    var name: String,

    @Column
    @field:Schema(
        description = "The price of a book",
        example = "5.99",
        type = "BigDecimal",
    )
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    @field:Schema(
        description = "The status of a book",
        example = "AVAILABLE",
        type = "BookStatus",
    )
    var status: BookStatus?,

    @ManyToOne
    @field:Schema(
        description = "The identification of the customer related to the book",
        example = "1945",
        type = "Integer",
    )
    @JoinColumn(name = "customer_id")
    var customer: Customer?
)

