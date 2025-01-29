package com.example.proyecto.domain.model

import com.google.firebase.firestore.DocumentId

data class Product(
    @DocumentId val id : Int,
    val name: String,
    val type: String,
    val description: String,
    val value: Int,
)
