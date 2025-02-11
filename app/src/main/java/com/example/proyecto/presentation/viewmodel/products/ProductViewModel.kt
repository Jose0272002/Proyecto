package com.example.proyecto.presentation.viewmodel.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.domain.model.Product
import com.example.proyecto.domain.usecase.products.AddProductsUseCase
import com.example.proyecto.domain.usecase.products.DeleteProductsUseCase
import com.example.proyecto.domain.usecase.products.ListProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    val listProductsUseCase: ListProductsUseCase,
    val deleteProductsUseCase: DeleteProductsUseCase
): ViewModel() {
    private val _products = listProductsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val products: StateFlow<List<Product>> = _products
    fun removeProduct(id: String) {
        viewModelScope.launch { deleteProductsUseCase(id) }
    }



}