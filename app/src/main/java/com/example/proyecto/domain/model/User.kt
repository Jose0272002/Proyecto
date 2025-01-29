package com.example.proyecto.domain.model

import android.provider.ContactsContract.CommonDataKinds.Email
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class User(
    @DocumentId val id : Int,
    val name: String,
    val email: Email,
    val age: Int = 0,
    @ServerTimestamp val createdAt: Date? =null
)
