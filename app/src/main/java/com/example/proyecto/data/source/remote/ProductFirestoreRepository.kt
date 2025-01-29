package com.example.proyecto.data.source.remote

import com.example.proyecto.domain.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ProductFirestoreRepository(val firestore: FirebaseFirestore) {

    private val collection = firestore.collection("products")

    fun list(): Flow<List<Product>> {
        return callbackFlow {

            val listener = collection
                // Aquí viene la query,
                // TODO como se ordena por id
                .orderBy("description", Query.Direction.DESCENDING)
                // Creamos un listener a la query para que se actualice siempre que haya cambios
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    val items = snapshots?.documents?.mapNotNull { doc ->
                        doc.toObject(Product::class.java)
                    } ?: emptyList()

                    trySend(items)
                }

            awaitClose() { listener.remove() }
        }
    }

    suspend fun save(product: Product) {
        collection.add(product).await()
    }

    suspend fun delete(id: String): Unit {
        collection
            .document(id)
            .delete()
            .await()

    }
}