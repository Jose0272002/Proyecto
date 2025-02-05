package com.example.proyecto.domain.usecase.products

import com.example.proyecto.data.source.remote.ProductFirestoreRepository

class DeleteProductsUseCase(val productsRepository: ProductFirestoreRepository) {

    suspend operator fun invoke(id: String): Result<Unit> {
        return try {
            productsRepository.delete(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}