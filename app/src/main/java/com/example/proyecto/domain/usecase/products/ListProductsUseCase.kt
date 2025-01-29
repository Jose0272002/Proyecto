package com.example.proyecto.domain.usecase.products
import com.example.proyecto.domain.model.Product
import com.example.proyecto.data.source.remote.ProductFirestoreRepository
import kotlinx.coroutines.flow.Flow


class ListProductsUseCase(
    private val productRepository: ProductFirestoreRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return productRepository.list()
    }
}