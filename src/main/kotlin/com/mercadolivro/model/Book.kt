package com.mercadolivro.model

import com.mercadolivro.model.BookStatus.ARCHIEVED
import com.mercadolivro.model.BookStatus.DELETED
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

    @ManyToOne
    @field:Schema(
        description = "The identification of the customer related to the book",
        example = "1945",
        type = "Integer",
    )
    @JoinColumn(name = "customer_id")
    var customer: Customer?
) {

    @Column
    @Enumerated(EnumType.STRING)
    @field:Schema(
        description = "The status of a book",
        example = "AVAILABLE",
        type = "BookStatus",
    )
    var status: BookStatus? = null
        set(value){
            if (field == DELETED || field == ARCHIEVED){
                throw Exception("Couldn't change book with status ${field!!.name}")
            }
            field = value
        }

    constructor(id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: Customer? =  null,
        status: BookStatus?) : this(id, name, price, customer) {
            this.status = status
        }
}