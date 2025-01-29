package com.example.proyecto.presentation.viewmodel.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.domain.model.Product
import com.example.proyecto.domain.usecase.products.DeleteProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    val deleteProductsUseCase: DeleteProductsUseCase
): ViewModel() {
    private val _products = MutableStateFlow(
        listOf(
            Product(1, "martillo", "herramienta", "martillo de mango rojo", 23),
            Product(2, "Tuerca", "material", "Tamaño pequeño", 2),
            Product(3, "Destornillador estrella", "herramienta", "Herramienta manual", 4),
            Product(4, "Martillo", "herramienta", "Martillo de plástico", 3),
            Product(5, "Desatascador", "herramienta", "Tamaño grande", 3),
            Product(6, "Destornillador plano", "herramienta", "Herramienta manual", 4)
        )
    )
    val products: StateFlow<List<Product>> = _products
    fun removeProduct(id: Int) {
        // TODO elminar en la base de datos
        viewModelScope.launch { deleteProductsUseCase(id) }

    }



}