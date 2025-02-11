package com.example.proyecto.domain.model

import com.google.firebase.firestore.DocumentId

data class Product(
    @DocumentId val id : String,
    val name: String,
    val type: String,
    val description: String,
    val value: Double?,
){
    constructor(): this(
        id = "",
        name = "",
        type = "",
        description = "",
        value = 0.0
    )
}
