package com.example.proyecto.presentation.viewmodel.products

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope
import com.example.proyecto.domain.model.Product
import com.example.proyecto.domain.usecase.products.AddProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddProductViewModel(
    val addProductUseCase: AddProductsUseCase
) : ViewModel() {
    private val _product = MutableStateFlow(
        Product()
    )
    val product: StateFlow<Product> = _product

    fun setName(name: String) {
        _product.value = _product.value.copy(
            name = name
        )
    }

    fun setType(type: String) {
        _product.value = _product.value.copy(
            type = type
        )
    }

    fun setValue(value: Double) {
        _product.value = _product.value.copy(
            value = value
        )
    }

    fun setDescription(description: String) {
        _product.value = _product.value.copy(
            description = description
        )
    }

    fun save() {
        viewModelScope.launch {
            addProductUseCase(product.value)
        }
    }
}