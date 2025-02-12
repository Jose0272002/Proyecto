package com.example.proyecto.domain.usecase.products

import com.example.proyecto.data.source.remote.ProductFirestoreRepository
import com.example.proyecto.domain.model.Product

class GetProductUseCase(
    private val productRepository: ProductFirestoreRepository
) {
    suspend operator fun invoke(productId: String?): Product? {
        return productRepository.getProductById(productId)
    }
}