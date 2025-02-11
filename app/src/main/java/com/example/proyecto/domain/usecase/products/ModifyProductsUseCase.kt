package com.example.proyecto.domain.usecase.products

import com.example.proyecto.data.source.remote.ProductFirestoreRepository
import com.example.proyecto.domain.model.Product

class ModifyProductsUseCase (
    private val productRepository: ProductFirestoreRepository
) {
    operator suspend fun invoke(product: Product): Unit {
        productRepository.save(product)
    }
}